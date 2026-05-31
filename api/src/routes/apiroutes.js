import { Router } from 'express'
import { viewFull, delById, insertData, viewId } from '../controllers/labController.js'

const apiRouter = Router()

apiRouter.get('/lab', viewFull)
apiRouter.post('/lab', insertData)
apiRouter.get('/lab/:id', viewId)
apiRouter.delete('/lab/:id', delById)

export default apiRouter
