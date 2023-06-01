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
    viewListBtn.addEventListener("click", function() {
      let tasksToSend = goToMainPage();
    });
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
   //window.location.href = "main.html";
    console.log(tasks);
    return tasks;
  }

  async function fetch331() {
    /*try {
      let params = new FormData();
      params.append("331", 331);
      let res = await fetch('http://localhost:8080/api/addCourse', {method: "POST", body: params});
      await statusCheck(res);
      res = await res.json();
      return res;
    } catch (err) {
      console.error(err);
    }*/
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
    try {
      let params = new FormData();
      params.append("312", 312);
      let res = await fetch('http://localhost:8080/api/addCourse', {method: "POST", body: params});
      await statusCheck(res);
      res = await res.json();
      console.log(res);
      temp(res);
    } catch (err) {
      console.error(err);
    }
  }

  async function fetch421() {
    try {
      let params = new FormData();
      params.append("421", 421);
      let res = await fetch('http://localhost:8080/api/addCourse', {method: "POST", body: params});
      await statusCheck(res);
      res = await res.json();
      return res;
    } catch (err) {
      console.error(err);
    }
  }

  async function fetch332() {
    try {
      let params = new FormData();
      params.append("332", 332);
      let res = await fetch('http://localhost:8080/api/addCourse', {method: "POST", body: params});
      await statusCheck(res);
      res = await res.json();
      return res;
    } catch (err) {
      console.error(err);
    }
  }

  function temp(response){

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