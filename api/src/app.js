
//express
import express from 'express';
import { json, urlencoded } from 'body-parser';
import path from 'path';
import apiRouter from './routes/apiroutes.js';

//env
require('dotenv').config({path: __dirname + "/../.env"})

const app = express()
const port = process.env.PORT

app.use(json())
app.use(urlencoded({extended: true}));

app.use('/v1/', apiRouter)

app.listen(port, () => {console.log(`server running at port ${port}`)})
