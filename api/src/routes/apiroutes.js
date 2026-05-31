import { Router } from 'express'
import { labDeleteById, labInsertData, labViewFull, labViewId, labUpdateId } from '../controllers/labController.js'
import { profDeleteById, profInsertData, profViewFull, profViewId, profUpdateId } from '../controllers/labController.js'
import { resDeleteById, resInsertData, resViewFull, resViewId, resUpdateId } from '../controllers/labController.js'

const apiRouter = Router()

apiRouter.get('/lab', labViewFull)
apiRouter.post('/lab', labInsertData)
apiRouter.get('/lab/:id', labViewId)
apiRouter.delete('/lab/:id', labDeleteById)
apiRouter.put('/lab/:id', labUpdateId)

apiRouter.get('/prof', profViewFull)
apiRouter.post('/prof', profInsertData)
apiRouter.get('/prof/:id', profViewId)
apiRouter.delete('/prof/:id', profDeleteById)
apiRouter.put('/prof/:id', profUpdateId)

apiRouter.get('/res', resViewFull)
apiRouter.post('/res', resInsertData)
apiRouter.get('/res/:id', resViewId)
apiRouter.delete('/res/:id', resDeleteById)
apiRouter.put('/res/:id', resUpdateId)


export default apiRouter
