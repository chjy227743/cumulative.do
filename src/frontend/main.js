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
    list.addEventListener("click", e => {finishedTask(e)});
    taskButton.addEventListener("click", e => {removeTask(e)});
    parent.appendChild(listItem);
  }

  function finishedTask(event) {
    let clickedElement = event.target;
    clickedElement.classList.toggle("finished");
    console.log("task is done");
    let parent = id("list");
    parent.removeChild(li);
  }

  /*function removeTask(event) {
    let clickedElement = event.target;
    let parent = id("list");
    parent.removeChild(li);
  }

  function sendDataToBackend(taskValue, dueDateValue) {
    event.preventDefault();

    // First, fetch the logged-in user's data
    // fetch('http://localhost:8080/api/getLoggedInUser')
    //     .then(response => response.json())
    //     .then(user => {
    //       // Now that we have the user's data, we can add an item to their todo list
    //       const userNtodoItem = {
    //         "userName": user.userName,
    //         "todo": taskValue,
    //         "completed": false,
    //         "dueDate": dueDateValue
    //       };
    //       return fetch('http://localhost:8080/api/addItem', {
    //         method: 'POST',
    //         headers: {
    //           'Content-Type': 'application/json'
    //         },
    //         body: JSON.stringify(userNtodoItem)
    //       });
    //     })
    //     .then(response => response.json())
    //     .then(newTodo => {
    //       // The new todo item was successfully added to the user's todo list
    //       console.log(newTodo);
    //     })
    //     .catch(error => {
    //       // Handle any errors
    //       console.error('Error:', error);
    //     });


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