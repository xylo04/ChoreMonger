package com.choremonger.gwt;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.XMLParser;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ChoreMonger implements EntryPoint {
	private static final int HTTP_OK = 200;
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	private Button closeButton;
	private DialogBox dialogBox;
	private Label errorLabel;
	private TextBox idField;
	private HTML serverResponseLabel;
	private Label textToServerLabel;
	private TextBox nameField;
	private TextBox streetField;
	private TextBox cityField;
	private TextBox stateField;
	private TextBox zipField;

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		createFields();
		createDialogBox();
		createButtons();
	}

	private void createFields() {
		idField = new TextBox();
		nameField = new TextBox();
		streetField = new TextBox();
		cityField = new TextBox();
		stateField = new TextBox();
		zipField = new TextBox();
		errorLabel = new Label();

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("idFieldContainer").add(idField);
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("streetFieldContainer").add(streetField);
		RootPanel.get("cityFieldContainer").add(cityField);
		RootPanel.get("stateFieldContainer").add(stateField);
		RootPanel.get("zipFieldContainer").add(zipField);

		// Focus the cursor on the name field when the app loads
		idField.setFocus(true);
		idField.selectAll();
	}

	private void createDialogBox() {
		dialogBox = new DialogBox();
		dialogBox.setText("RESTful Call");
		dialogBox.setAnimationEnabled(true);
		closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		textToServerLabel = new Label();
		serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		closeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
	}

	/**
	 * Send the name from the nameField to the server and wait for a response.
	 */
	private void sendToServer(RequestBuilder.Method httpMethod) {
		// Then, we send the input to the server.
		textToServerLabel.setText("");
		serverResponseLabel.setText("");

		RequestBuilder request = buildRequest(httpMethod);
		request.setTimeoutMillis(10000);
		request.setCallback(new RequestCallback() {

			@Override
			public void onError(Request request, Throwable exception) {
				// Show the error message to the user
				dialogBox.setText("RESTful Call - Failure");
				serverResponseLabel.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR);
				dialogBox.center();
				closeButton.setFocus(true);

			}

			@Override
			public void onResponseReceived(Request request, Response response) {
				dialogBox.setText("RESTful Call");
				serverResponseLabel.removeStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(response.getStatusCode() + " "
						+ response.getStatusText() + "<p>" + response.getText());
				dialogBox.center();
				closeButton.setFocus(true);

				if (response.getStatusCode() == HTTP_OK) {
					parseXml(response.getText());
				}
			}
		});
		try {
			request.send();
		} catch (RequestException re) {
			re.printStackTrace();
		}
	}

	private void parseXml(String messageXml) {
		try {
			Document messageDom = XMLParser.parse(messageXml);

			Node customerNode = messageDom.getElementsByTagName("customer")
					.item(0);
			String id = ((Element) customerNode).getAttribute("id");
			idField.setText(id);

			String name = messageDom.getElementsByTagName("fullname").item(0)
					.getFirstChild().getNodeValue();
			nameField.setText(name);

			String street = messageDom.getElementsByTagName("street").item(0)
					.getFirstChild().getNodeValue();
			streetField.setText(street);

			String city = messageDom.getElementsByTagName("city").item(0)
					.getFirstChild().getNodeValue();
			cityField.setText(city);

			String state = messageDom.getElementsByTagName("state").item(0)
					.getFirstChild().getNodeValue();
			stateField.setText(state);

			String zip = messageDom.getElementsByTagName("zip").item(0)
					.getFirstChild().getNodeValue();
			zipField.setText(zip);
		} catch (DOMException e) {
			Window.alert("Could not parse XML document.");
		}
	}

	private RequestBuilder buildRequest(RequestBuilder.Method httpMethod) {
		String resourceId = idField.getText();
		String resourceUrl = "/resources/customers/";

		if (httpMethod != RequestBuilder.POST) {
			resourceUrl += URL.encode(resourceId);
		}
		RequestBuilder request = new RequestBuilder(httpMethod, resourceUrl);
		if (httpMethod == RequestBuilder.PUT
				|| httpMethod == RequestBuilder.POST) {
			if (resourceId.equals("")) {
				resourceId = "0";
			}
			request.setRequestData("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><customer id=\""
					+ resourceId
					+ "\"><fullname>"
					+ nameField.getText()
					+ "</fullname>"
					+ "<address><street>"
					+ streetField.getText()
					+ "</street><city>"
					+ cityField.getText()
					+ "</city><state>"
					+ stateField.getText()
					+ "</state><zip>"
					+ zipField.getText() + "</zip></address></customer>");
			request.setHeader("Content-Type", "application/xml; charset=utf-8");
		}
		return request;
	}

	private void createButtons() {
		final Button getButton = new Button("Get");
		final Button postButton = new Button("Post");
		final Button putButton = new Button("Put");
		final Button deleteButton = new Button("Delete");
		getButton.addStyleName("sendButton");
		postButton.addStyleName("sendButton");
		putButton.addStyleName("sendButton");
		deleteButton.addStyleName("sendButton");
		RootPanel.get("getButtonContainer").add(getButton);
		RootPanel.get("postButtonContainer").add(postButton);
		RootPanel.get("putButtonContainer").add(putButton);
		RootPanel.get("deleteButtonContainer").add(deleteButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Add a handler to send the name to the server
		getButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				sendToServer(RequestBuilder.GET);
			}
		});
		postButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				sendToServer(RequestBuilder.POST);
			}
		});
		putButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				sendToServer(RequestBuilder.PUT);
			}
		});
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				sendToServer(RequestBuilder.DELETE);
			}
		});
	}
}
