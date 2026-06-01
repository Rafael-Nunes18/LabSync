import supabase from '../config/db.js'

export async function viewTable(table, param, value) {
  const { data } = (param||value) === undefined 
    ? await supabase.from(table).select() 
    : await supabase.from(table).select().eq(param, value)
  return data
}

export async function updateTable(table, param, value, data) {
  if (param == null || value == null || data == null) throw new Error("Missing parameters")
  const { data: result } = await supabase.from(table).update(data).eq(param, value).select()
  return result
}

export async function insertTable(table, data) {
  const { data: result } = await supabase.from(table).insert(data).select()
  return result
}

export async function deleteFrom(table, value) {
  const result = await supabase.from(table).delete().eq('id', value)
  return result
}
