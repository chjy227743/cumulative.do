'use strict';
(function() {

  let username;
  let password;

  window.addEventListener('load', init);

  /**
   * initiates page upon load.
   */
  function init() {
    let taskInserterBtn = id("taskInserterBtn");
    taskInserterBtn.addEventListener("click", insertTask);
  }

  function insertTask() {
    let task = id("inputForm1");
    let taskValue = task.value;
    let dueDate = id("inputForm3");
    let dueDateValueString = dueDate.value;
    let dueDateValue = new Date(dueDateValueString);
   // sendDataToBackend(taskValue, dueDateValue);
    let listItem = document.createElement("li");
    let subcontainer = document.createElement("article");
    subcontainer.setAttribute("id", "to-do-list-parent");
    let subListItem1 = document.createElement("section");
    let subListItem3 = document.createElement("section");
    let subListItem4 = document.createElement("section");
    subListItem1.setAttribute("class", "to-do-list-box");
    subListItem1.setAttribute("id", "to-do-list-box-1");
    subListItem3.setAttribute("class", "to-do-list-box");
    subListItem3.setAttribute("id", "to-do-list-box-3");
    let taskButton = document.createElement("button");
    taskButton.textContent = "Finished";
    taskButton.setAttribute("class", "taskButton");
    subListItem4.setAttribute("class", "to-do-list-box");
    subListItem4.setAttribute("id", "to-do-list-box-4");
    subListItem4.appendChild(taskButton);
    let subListItem1Content = document.createElement("p");
    let subListItem3Content = document.createElement("p");
    subListItem1Content.textContent = taskValue;
    subListItem3Content.textContent = dueDateValue;
    subListItem1.appendChild(subListItem1Content);
    subListItem3.appendChild(subListItem3Content);
    subcontainer.appendChild(subListItem1);
    subcontainer.appendChild(subListItem3);
    subcontainer.appendChild(subListItem4)
    listItem.appendChild(subcontainer);
    let parent = id("list");
    //list.addEventListener("click", e => {finishedTask(e)});
    taskButton.addEventListener("click", e => {finishTask(e)});
    parent.appendChild(listItem);
  }

  function finishTask(event) {
    let elementToRemove = event.target.parentNode.parentNode.parentNode;
    console.log("task is done");
    let parent = id("list");
    parent.removeChild(elementToRemove);
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