
import supabase from '../config/db.js'
export async function viewTable(table, param, value) {
  const query = (param||value) === undefined ? await supabase.from(table).select() : await supabase.from(table).select().eq(param, value)
  return query.data
}
export async function updateTable(table, param, value, data) {
  const query = (param||value||data) === undefined ? new Error("Missing parameters") : await supabase.from(table).update(data).eq(param, value)
  return query.data
}
export async function insertTable(table, data) {
  const res = await supabase.from(table).insert(data)
  return res
}
export async function deleteFrom(table, value) {
  let del = await supabase.from(table).delete().eq('id', value)
  return del
}
