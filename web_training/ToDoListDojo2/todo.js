(function () {
    "use strict";
    var text = document.getElementById("todoInput");
    var btn = document.getElementById("addBtn");
    var todoList = document.getElementById("todos");

    var makeTodoHtml = function (todoString, checkHandler) {
        var element = document.createElement("li");
        var checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        var span = document.createElement("span");
        span.textContent = todoString;
        element.appendChild(checkbox);
        element.appendChild(span);
        checkbox.addEventListener("click", checkHandler);

        return element;
    };
    
    var onChecked = function (event) {
        var target = event.target;
        console.log(target);
        if(target.checked)
        {
            target.parentElement.classList.add("done");
        }
        else {
            target.parentElement.classList.remove("done");
        }
    };

    var addButtonClicked = function (event) {
        var todoText = text.value;
        todoList.appendChild(makeTodoHtml(todoText, onChecked))
        text.value = "";
        text.focus();
    };

    btn.addEventListener("click", addButtonClicked);
    function inputKeyup(event) {
      if(event.keyCode == 13)
      {
          btn.click();
      }
    };

    text.addEventListener("keyup", inputKeyup)
    text.focus();

}) ();