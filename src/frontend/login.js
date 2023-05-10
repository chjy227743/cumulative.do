'use strict';
(function() {

  let username;
  let password;

  window.addEventListener('load', init);

  /**
   * initiates page upon load.
   */
  function init() {
    let loginBtn = id("login-button");
    loginBtn.addEventListener("click", goToMainPage);
  }

  function goToMainPage() {
    let uName = id("loginForm1").value;
    let pswd = id("loginForm2").value;
    username = uName;
    password = pswd;
    window.location.href = "main.html";
  }


/*let formValue = task.value;
  let listItem = document.createElement("li");
  listItem.textContent = formValue;
  let parent = id("list");
  listItem.addEventListener("click", e => {finishedTask(e)});
  parent.appendChild(listItem); */


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