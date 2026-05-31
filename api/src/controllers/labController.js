import { viewTable, insertCnpq, deleteFrom } from '../utils/dbFunctions.js'
const viewFull = async (req, res) => {
  viewTable('cnpq')
    .then(result => res.json(result.rows))
}
const viewId = async (req, res) => {
  viewTable('cnpq', 'id', req.params.id)
    .then((result) => res.json(result.rows[0]))
}
const insertData = async (req, res) => {
  const { nome, subarea } = req.body
  if (nome == null || subarea == null) {
    res.send("MISSING DATA")
    return console.error("ERROR:MISSING DATA")
  }
  insertCnpq(nome, subarea)
    .then((result) => res.json(req.body))
}

const delById = async (req, res) => {
  deleteFrom('cnpq', req.params.id)
    .then((result) => res.json(result.rowCount === 0 ? "ID not found on database" : `Row with ID ${req.params.id} deleted successfully`))
}
export default {
  viewFull,
  delById,
  viewId,
  insertData
}
