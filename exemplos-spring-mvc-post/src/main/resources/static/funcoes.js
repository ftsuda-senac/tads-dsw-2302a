function getJson(url) {
  // const promise = new Promise(function(resolve, reject) {
  const promise = new Promise((resolve, reject) => {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.onload = function () {
      if (xhr.status >= 200 && xhr.status < 400) {
        resolve(JSON.parse(xhr.responseText));
      } else {
        const erro = {
          codigo: xhr.status,
          mensagem: 'Erro na requisição'
        }
        reject(erro);
      }
    };
    xhr.send();
  });
  return promise;
}

function postJson(url, dados) {
  const promise = new Promise((resolve, reject) => {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);
    xhr.setRequestHeader('content-type', 'application/json')
    xhr.onload = function () {
      if (xhr.status >= 200 && xhr.status < 400) {
        // SUCESSO
        if (xhr.responseText) {
          resolve(JSON.parse(xhr.responseText));
        } else {
          resolve();
        }
      } else {
        // ERROS
        const objErro = {
          codigo: xhr.status,
          mensagem: 'Erro'
        };
        reject(objErro);
      }
    };
    xhr.send();
  });
  return promise;
}
