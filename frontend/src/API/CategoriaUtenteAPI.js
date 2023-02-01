export class CategoriaUtenteAPI {

  async deleteRotazione(idRotazione, idUtente){
    const response = await fetch('/api/categorie/rotazione_id='+idRotazione+'/utente_id='+idUtente, { method: 'DELETE' });
    return response.status;
  }

  async getCategoriaUtente(idUtente) {
    const response = await fetch('/api/categorie/stato/utente_id='+idUtente);
    const body = await response.json();
    const categorie = [];

    for (let i = 0; i < body.length; i++) {
      let categoria = new Object();
      categoria.categoriaUtenteId = body[i].id
      categoria.categoria = body[i].categoria.nome
      categoria.inizio = body[i].inizioValidita
      categoria.fine = body[i].fineValidita
      categorie[i]=categoria
    }

    return categorie;

  }

  async getSpecializzazioniUtente(idUtente) {
    const response = await fetch('/api/categorie/specializzazioni/utente_id='+idUtente);
    const body = await response.json();
    const categorie = [];

    for (let i = 0; i < body.length; i++) {
      let categoria = new Object();
      categoria.categoriaUtenteId = body[i].id
      categoria.categoria = body[i].categoria.nome
      categoria.inizio = body[i].inizioValidita
      categoria.fine = body[i].fineValidita
      categorie[i]=categoria
    }

    return categorie;
  }

  async getTurnazioniUtente(idUtente) {
    const response = await fetch('/api/categorie/turnazioni/utente_id='+idUtente);
    const body = await response.json();
    const categorie = [];

    for (let i = 0; i < body.length; i++) {
      let categoria = new Object();
      categoria.categoriaUtenteId = body[i].id
      categoria.categoria = body[i].categoria.nome
      categoria.inizio = body[i].inizioValidita
      categoria.fine = body[i].fineValidita
      categorie[i]=categoria
    }

    return categorie;
  }

  async postAggiungiTurnazione(categoria,dataInizio,dataFine, utente_id) {

    let cat = new Object;
    cat.nome = categoria;
    cat.tipo = 2; // = tipo turnazione

    let turnazione = new Object();

    turnazione.categoria = cat
    turnazione.inizioValidita= new Date(dataInizio.$d.getTime() - (dataInizio.$d.getTimezoneOffset() * 60000 )).toISOString();
    turnazione.fineValidita = new Date(dataFine.$d.getTime() - (dataInizio.$d.getTimezoneOffset() * 60000 )).toISOString();

    console.log("turnazioni:")
    console.log(turnazione)

    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(turnazione)
    };
    const url = "/api/categorie/turnazioni/utente_id=" + utente_id;
    const response = await fetch(url , requestOptions);
    return response.status;

  }
}
