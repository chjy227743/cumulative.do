'use strict';
(function() {

  window.addEventListener('load', init);

  /**
   * initiates page upon load.
   */
  function init() {
    let taskInserterBtn = id("taskInserterBtn");
    taskInserterBtn.addEventListener("click", insertTask);
  }

  function insertTask() {
    let task = document.getElementById("inputForm");
    let formValue = task.value;
    let listItem = document.createElement("li");
    listItem.textContent = formValue;
    let parent = id("list");
    listItem.addEventListener("click", e => {finishedTask(e)});
    parent.appendChild(listItem);
  }

  function finishedTask(event) {
    let clickedElement = event.target;
    clickedElement.classList.toggle("finished");
    console.log("task is done");
  }

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