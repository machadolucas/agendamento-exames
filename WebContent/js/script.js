function disable_enter_key(evt) {
	var ret = true;

	var evt = evt || event;
	if ((evt.keyCode || event.which || event.charCode || 0) == 13) {
		if (evt.stopPropagation) {
			evt.stopPropagation();
			evt.preventDefault();
		}
		ret = false;
	}

	return ret;

}

function clear_datatable_selection() {

	var inputSelection = $("input[id$='dataTable_selection']");
	if (inputSelection == null) {
		return;
	}
	inputSelection.attr('value', null);

}

function reset_clear_datatable_selection() {

	window.clear_datatable_selection = function() {
	};

}

function disable_enter_key_in_all_inputs() {

	var forms = document.getElementsByTagName("form");
	if (forms == null || forms.length == 0) {
		return;
	}

	for ( var idx = 0; idx < forms.length; idx++) {

		var form = forms[idx];

		if (form.addEventListener) {
			form.addEventListener('keypress', disable_enter_key, false);
		} else if (form.attachEvent) {
			form.attachEvent('onkeypress', disable_enter_key);
		}

	}
}

function on_body_load() {

	disable_enter_key_in_all_inputs();
	clear_datatable_selection();

}

function shake_dialog(prime_dialog_widget, xhr, status, args) {

	var shake = false;

	// global error messages
	var globalErrArray = $('.ui-messages-error');
	if (globalErrArray.length > 0) {
		shake = true;
	}

	if (args != null) {
		var dialog_jqid = prime_dialog_widget.jqId;
		if (args.validationFailed) {
			shake = true;
		} else {
			// widget error message
			var widgetErrArray = $('#' + prime_dialog_widget.id
					+ ' .ui-message-error');
			if (widgetErrArray.length > 0) {
				shake = true;
			}
		}
	}

	if (shake) {
		jQuery(dialog_jqid).effect("shake", {
			times : 3
		}, 70);
	} else {
		prime_dialog_widget.hide();
	}

	return shake;
}


function handleDetailsPopupRequest(xhr, status, args) {
	if (args.canContinue) {
		detailsDialogWid.show();
	} else {
		detailsDialogWid.hide();
	}
}


function handleDeletePopupRequest(xhr, status, args) {
	if (args.canContinue) {
		confirmDeleteDialogWid.show();
	} else {
		confirmDeleteDialogWid.hide();
	}
}