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
