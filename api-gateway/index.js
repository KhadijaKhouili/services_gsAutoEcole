const express = require('express');
const app = express();
const os = require('os');
const rateLimit = require('express-rate-limit');
const httpProxy = require('express-http-proxy')
const cors = require('cors');
const axios = require('axios');
const jwt = require('jsonwebtoken');
const bodyParser = require('body-parser')

const auth_url = process.env.AUTH_SERVICE;
const depenses_url = process.env.DEPENSES_SERVICE
const cours_url = process.env.COURS_SERVICE
const employees_url = process.env.EMPLOYEES_SERVICE
const condidats_url = process.env.CONDIDATS_SERVICE
const vehicle_url = process.env.VEHICULE_SERVICE

// const JWT_SECRET = process.env.JWT_SECRET
// const jwtsecret = jwt.sign(JWT_SECRET, JWT_SECRET);

// const authServiceProxy = httpProxy(auth_url);
// const accountsServiceProxy = httpProxy(auth_url, {
//     proxyReqPathResolver: function (req) {
// 		return `/accounts${req.url}`
//     }
// });

const depensesServiceProxy = httpProxy(depenses_url);
const coursServiceProxy = httpProxy(cours_url);
const employeesServiceProxy = httpProxy(employees_url);
const condidatsServiceProxy = httpProxy(condidats_url);
const vehicleServiceProxy = httpProxy(vehicle_url);

const limiter = rateLimit({
	windowMs: 15 * 60 * 1000,
	max: 1000,
	standardHeaders: true,
	legacyHeaders: false
});

// const jwtmiddleware = (req, res, next) => {

// 	req.headers['X-JWT-SECRET'] = jwtsecret;

// 	next()
// }

const authentification = async (req, res, next) => {
	if( req.get('Authorization') ) {
		const instance = axios.create({
			baseURL: `http://${auth_url}`,
			headers: {
				'Authorization': req.get('Authorization'),
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			}
		});

		try {
			const hed = await instance.post('/').then((response) => response.data);
			req.headers['X-AUTH'] = jwt.sign(hed, jwtsecret, { algorithm: 'HS256' })
		} catch (error) {}
	}

	next();
}

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())
//app.use(jwtmiddleware)
app.use(limiter);
app.use(cors());

//Proxing
app.use("/depense", depensesServiceProxy);
app.use("/cour", coursServiceProxy);
app.use("/employe", employeesServiceProxy);
app.use("/condidat", condidatsServiceProxy);
app.use("/vehicle", vehicleServiceProxy);

app.all('/', authentification, (req, res) => {
    res.send(`Unaccessible`).status(403);
});

const port = process.env.PORT || 3000;
app.listen(port, () => {
    console.log(`Server listening on port ${port}`);
});