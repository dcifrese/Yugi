$ ->
  $.get "/tasks", (data) ->
    $.each data, (index, task) ->
      $("#tasks").append $("<li>").text task.title
      $("#tasks").append $("<li>").text task.description