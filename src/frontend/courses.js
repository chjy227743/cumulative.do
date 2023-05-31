'use strict';
(function() {

  let cse331Selected;
  let cse312Selected;
  let cse421Selected;
  let cse332Selected;
  let noneSelected;

  window.addEventListener('load', init);

  /**
   * initiates page upon load.
   */
  function init() {
    let buttonFor331 = id("cse331Button");
    buttonFor331.addEventListener("click", e => {selected(e)});
    let buttonFor312 = id("cse312Button");
    buttonFor312.addEventListener("click", e => {selected(e)});
    let buttonFor421 = id("cse421Button");
    buttonFor421.addEventListener("click", e => {selected(e)});
    let buttonFor332 = id("cse332Button");
    buttonFor332.addEventListener("click", e => {selected(e)});
    let noneButton= id("noneButton");
    noneButton.addEventListener("click", e => {selectedNone(e)});
    let viewListBtn = id("goToMainPageButton");
    viewListBtn.addEventListener("click", goToMainPage);
  }

  function selected(event) {
    let buttonSelected = event.target;
    buttonSelected.classList.toggle("selected");
    let noneButton= id("noneButton");
    noneButton.classList.remove("selected");
  }

  function selectedNone(event) {
    let buttonSelected = event.target;
    buttonSelected.classList.toggle("selected");
    let buttonFor331 = id("cse331Button");
    buttonFor331.classList.remove("selected");
    let buttonFor312 = id("cse312Button");
    buttonFor312.classList.remove("selected");
    let buttonFor421 = id("cse421Button");
    buttonFor421.classList.remove("selected");
    let buttonFor332 = id("cse332Button");
    buttonFor332.classList.remove("selected");
  }

  function goToMainPage() {
    // Prevent the form from being submitted the default way
    event.preventDefault();

    fetch('http://localhost:8080/api/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        "cse331": cse331Selected,
        "cse312": cse312Selected,
        "cse421": cse421Selected,
        "cse332": cse332Selected,
        "none": noneSelected
      })
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch((error) => {
      console.error('Error:', error);
    });
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