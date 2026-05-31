import { viewTable, insertTable, deleteFrom, updateTable } from '../utils/dbFunctions.js'

//LABS:
export const labViewFull = async (req, res) => {
  await viewTable('laboratorios')
    .then(result => res.status(200).json(result));
}

export const labViewId = async (req, res) => {
  viewTable('laboratorios', 'id', req.params.id)
    .then((result) => res.status(200).json(result[0]))
}

export const labInsertData = async (req, res) => {
  const { numero, tipo, capacidade, ativo } = req.body;

  if (numero == null || tipo == null || capacidade == null || ativo == null) {
    return res.status(400).send("Missing required fields.");
  }

  insertTable('laboratorios', {numero, tipo, capacidade, ativo})
    .then((result) => res.status(201).json(result))
}

export const labDeleteById = async (req, res) => {
  deleteFrom('laboratorios', req.params.id)
    .then((result) => {
      let failed = result.rowCount === 0
      res.status(failed ? 404 : 200).json(failed ? "ID not found on database" : `Row with ID ${req.params.id} deleted successfully`);
    })
}

export const labUpdateId = async (req, res) => {
  const { numero, tipo, capacidade, ativo } = req.body;

  if (numero == null || tipo == null || capacidade == null || ativo == null) {
    return res.status(400).send("Missing required fields.");
  }

  updateTable('laboratorios', 'id', req.params.id, {numero, tipo, capacidade, ativo})
    .then((result) => res.status(200).json(result))
}

//PROFS:
export const profViewFull = async (req, res) => {
  await viewTable('professores')
    .then(result => res.status(200).json(result));
}

export const profViewId = async (req, res) => {
  viewTable('professores', 'id', req.params.id)
    .then((result) => res.status(200).json(result[0]))
}

export const profInsertData = async (req, res) => {
  const { nome, email, senha, perfil } = req.body;

  if (nome == null || email == null || senha == null || perfil == null) {
    return res.status(400).send("Missing required fields.");
  }

  insertTable('professores', {nome, email, senha, perfil})
    .then((result) => res.status(201).json(result))
}

export const profDeleteById = async (req, res) => {
  deleteFrom('professores', req.params.id)
    .then((result) => {
      let failed = result.rowCount === 0
      res.status(failed ? 404 : 200).json(failed ? "ID not found on database" : `Row with ID ${req.params.id} deleted successfully`);
    })
}

export const profUpdateId = async (req, res) => {
  const { nome, email, senha, perfil } = req.body;

  if (nome == null || email == null || senha == null || perfil == null) {
    return res.status(400).send("Missing required fields.");
  }

  updateTable('professores', 'id', req.params.id, {nome, email, senha, perfil})
    .then((result) => res.status(200).json(result))
}

//RESERVES:
export const resViewFull = async (req, res) => {
  await viewTable('reservas')
    .then(result => res.status(200).json(result));
}

export const resViewId = async (req, res) => {
  viewTable('reservas', 'id', req.params.id)
    .then((result) => res.status(200).json(result[0]))
}

export const resInsertData = async (req, res) => {
  const { revisado_por, professor_id, laboratorio_id, status, turma, data, hora_inicio, hora_fim } = req.body;

  if (revisado_por == null || professor_id == null || laboratorio_id == null || status == null || turma == null || data == null || hora_inicio == null || hora_fim == null) {
    return res.status(400).send("Missing required fields.");
  }

  insertTable('reservas', {revisado_por, professor_id, laboratorio_id, status, turma, data, hora_inicio, hora_fim})
    .then((result) => res.status(201).json(result)).catch(err => console.log(err));
}

export const resDeleteById = async (req, res) => {
  deleteFrom('reservas', req.params.id)
    .then((result) => {
      let failed = result.rowCount === 0
      res.status(failed ? 404 : 200).json(failed ? "ID not found on database" : `Row with ID ${req.params.id} deleted successfully`);
    })
}

export const resUpdateId = async (req, res) => {
  const { revisado_por, professor_id, laboratorio_id, status, turma, data, hora_inicio, hora_fim } = req.body;

  if (revisado_por == null || professor_id == null || laboratorio_id == null || status == null || turma == null || data == null || hora_inicio == null || hora_fim == null) {
    return res.status(400).send("Missing required fields.");
  }

  updateTable('reservas', 'id', req.params.id, {revisado_por, professor_id, laboratorio_id, status, turma, data, hora_inicio, hora_fim})
    .then((result) => res.status(200).json(result))
}