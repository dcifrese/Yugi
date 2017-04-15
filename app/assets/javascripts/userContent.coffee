$ ->
  $.get "/deck", (data) ->
    $.each data, (index, card) ->
      $("#deck").append $("<li>").text card.description
