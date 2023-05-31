'use strict';
(function() {

  window.addEventListener('load', init);

  /**
   * initiates page upon load.
   */
  function init() {
    let buttonFor331 = id("cse331Button");
    buttonFor331.addEventListener("click", goToMainPage);
    let buttonFor312 = id("cse312Button");
    buttonFor312.addEventListener("click", goToMainPage);
    let buttonFor421 = id("cse421Button");
    buttonFor421.addEventListener("click", goToMainPage);
    let buttonFor332 = id("cse332Button");
    buttonFor332.addEventListener("click", goToMainPage);
    let noneButton= id("noneButton");
    noneButton.addEventListener("click", goToMainPage);
  }

  function goToMainPage() {
    window.location.href = "main.html";
  }

/*
    fetch('http://localhost:8080/api/addItem', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        "todo": taskValue,
        "completed": false,
        "dueDate": dueDateValue
      })
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch((error) => {
      console.error('Error:', error);
    });
  }

*/
  /**
   * Returns the element that has the ID attribute with the specified value.
   * @param {string} id - element ID.
   * @returns {object} - DOM object associated with id.
   */
  function id(id) {
    return document.getElementById(id);
  }

  /**
   * Returns first element matching selector.
   * @param {string} selector - CSS query selector.
   * @returns {object} - DOM object associated selector.
   */
  function qs(selector) {
    return document.querySelector(selector);
  }

  /**
   * Returns the array of elements that match the given CSS selector.
   * @param {string} query - CSS query selector
   * @returns {object[]} array of DOM objects matching the query.
   */
  function qsa(query) {
    return document.querySelectorAll(query);
  }

})();