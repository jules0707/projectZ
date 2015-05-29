$(function() {

$("#message_remove_property").click(
  function() {  
	$("#dialog_button_confirm_remove").dialog({
		autoOpen : true,
		dialogClass : "no-show-title",
		resizable : false,
		draggable : false,
		modal : true,
		width : "auto",
		height : "auto",
		buttons : [ {
			text : $("#dialog_button_yes").text(),
			click : function() {
				 location.href = "../remove_listing/"
	              + $("#property_idref").val();
				 }
		},
		{
			text : $("#dialog_button_cancel").text(),
			click : function() {
				$(this).dialog("close");
			}
		} ]
	});  
	return false;
  });
});

