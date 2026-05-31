//env
import dotenv from 'dotenv/config.js'
//express
import express from 'express';
import bp from 'body-parser';
import path from 'path';
import apiRouter from './routes/apiroutes.js';


const {json, urlencoded} = bp
const app = express()
const port = process.env.PORT

app.use(json())
app.use(urlencoded({extended: true}));

app.use('/v1/', apiRouter)

app.listen(port, () => {console.log(`server running at port ${port}`)})
