'use strict';
(function() {

  let cse331Selected = false;
  let cse312Selected = false;
  let cse421Selected = false;
  let cse332Selected = false;
  let noneSelected = false;
  let tasks = [];

  window.addEventListener('load', init);

  /**
   * initiates page upon load.
   */
  function init() {
    let buttonFor331 = id("cse331Button");
    buttonFor331.addEventListener("click", e => {selected331(e)});
    let buttonFor312 = id("cse312Button");
    buttonFor312.addEventListener("click", e => {selected312(e)});
    let buttonFor421 = id("cse421Button");
    buttonFor421.addEventListener("click", e => {selected421(e)});
    let buttonFor332 = id("cse332Button");
    buttonFor332.addEventListener("click", e => {selected332(e)});
    let noneButton= id("noneButton");
    noneButton.addEventListener("click", e => {selectedNone(e)});
    let viewListBtn = id("goToMainPageButton");
    viewListBtn.addEventListener("click", goToMainPage);
  }

  function selected331(event) {
    cse331Selected = true;
    let buttonSelected = event.target;
    buttonSelected.classList.toggle("selected");
    let noneButton= id("noneButton");
    noneButton.classList.remove("selected");
  }

  function selected312(event) {
    cse312Selected = true;
    let buttonSelected = event.target;
    buttonSelected.classList.toggle("selected");
    let noneButton= id("noneButton");
    noneButton.classList.remove("selected");
  }

  function selected421(event) {
    cse421Selected = true;
    let buttonSelected = event.target;
    buttonSelected.classList.toggle("selected");
    let noneButton= id("noneButton");
    noneButton.classList.remove("selected");
  }

  function selected332(event) {
    cse332Selected = true;
    let buttonSelected = event.target;
    buttonSelected.classList.toggle("selected");
    let noneButton= id("noneButton");
    noneButton.classList.remove("selected");
  }

  function selectedNone(event) {
    noneSelected = true;
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
    console.log(cse332Selected);
    console.log(cse312Selected);
    console.log(cse421Selected);
    console.log(cse331Selected);
    console.log(noneSelected);
    // Prevent the form from being submitted the default way
    /*event.preventDefault();

    fetch('http://localhost:8080/api/addCourse', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        "331": cse331Selected,
        "312": cse312Selected,
        "421": cse421Selected,
        "332": cse332Selected,
        "none": noneSelected
      })
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch((error) => {
      console.error('Error:', error);
    });*/

    if(cse312Selected === true) {
      tasks[0] = fetch312();
    }
    if(cse331Selected === true) {
      tasks[1] = fetch331();
    }
    if(cse332Selected === true) {
      tasks[2] = fetch332();
    }
    if(cse421Selected === true) {
      tasks[3] = fetch421();
    }
    window.location.href = "main.html";
    console.log(tasks);
    populateToDoList(tasks);
  }

  async function fetch331() {
    event.preventDefault();

    fetch('http://localhost:8080/api/addCourse', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(331)
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch((error) => {
      console.error('Error:', error);
    });
    return response;
  }

  async function fetch312() {
    event.preventDefault();

    fetch('http://localhost:8080/api/addCourse', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(312)
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch((error) => {
      console.error('Error:', error);
    });
    return response;
  }

  async function fetch421() {
    event.preventDefault();

    fetch('http://localhost:8080/api/addCourse', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(421)
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch((error) => {
      console.error('Error:', error);
    });
    return response;
  }

  async function fetch332() {
    event.preventDefault();

    fetch('http://localhost:8080/api/addCourse', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(332)
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch((error) => {
      console.error('Error:', error);
    });
    return response;
  }

  function populateToDoList(tasksArray){
    if(tasksArray.length > 0) {
      //array @ index is an entire json object
      for(let index = 0; index < tasksArray.length; index++) {
        let currentAssignment = tasksArray[index];
        let taskValue = currentAssignment.todo;
        let dueDate = currentAssignment.dueDate;
        insertTask(taskValue, dueDate)
      }
    }
  }

  function insertTask(taskValueGiven, dueDateGiven) {
    console.log("entered")
    let taskValue = taskValueGiven;
    let dueDateValueString = 5/5/5;
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