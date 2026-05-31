
import supabase from '../config/db.js'
export async function viewTable(table, param, value) {
  const tab = table.toString()
  const query = param === undefined ? `SELECT * FROM ${tab}` : `SELECT * FROM ${tab} WHERE ${param.toString()} = ${value.toString()}`
  const data = await _query(query)
  return data
}

export async function insertCnpq(areas, subareas) {
  const sub = subareas.map((item) => item.toString())
  const query = {
    text: 'INSERT INTO cnpq(nome, subareas) VALUES($1, $2)',
    values: [areas.toString(), sub]
  }
  const res = await _query(query)
  return res
}
export async function deleteFrom(table, value) {
  const query = {
    text: `DELETE FROM ${table.toString()} WHERE id = $1`,
    values: [Number(value)]
  }
  let del = await _query(query)
  return del
}
