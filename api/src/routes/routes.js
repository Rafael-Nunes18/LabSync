const router = require('express').Router()
import { viewFull, delById, insertData, viewId } from '../controllers/cnpqControllers'

router.get('/', viewFull)
router.post('/', insertData)
router.get('/:id', viewId)
router.delete('/:id', delById)

export default router
