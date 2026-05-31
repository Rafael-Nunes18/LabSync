import { viewTable, insertTable, deleteFrom, updateTable } from '../utils/dbFunctions.js'
export const viewFull = async (req, res) => {
  viewTable('cnpq')
    .then(result => res.json(result.rows))
}
export const viewId = async (req, res) => {
  viewTable('cnpq', 'id', req.params.id)
    .then((result) => res.json(result.rows[0]))
}
export const insertData = async (req, res) => {
  const { nome, subarea } = req.body
  if (nome == null || subarea == null) {
    res.send("MISSING DATA")
    return console.error("ERROR:MISSING DATA")
  }
  insertTable(nome, subarea)
    .then((result) => res.json(req.body))
}

export const delById = async (req, res) => {
  deleteFrom('cnpq', req.params.id)
    .then((result) => res.json(result.rowCount === 0 ? "ID not found on database" : `Row with ID ${req.params.id} deleted successfully`))
}

