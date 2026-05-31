import { Router } from 'express'
import { labDeleteById, labInsertData, labViewFull, labViewId } from '../controllers/labController.js'

const apiRouter = Router()

apiRouter.get('/lab', labViewFull)
apiRouter.post('/lab', labInsertData)
apiRouter.get('/lab/:id', labViewId)
apiRouter.delete('/lab/:id', labDeleteById)

export default apiRouter
