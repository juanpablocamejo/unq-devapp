package ar.edu.unq.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.unq.Person;
import ar.edu.unq.services.PersonService;

public class Home extends WebPage {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "service.person")
    PersonService personService;

    public Home() {
        this.initialize();
    }

    public final void initialize() {
        Label markupLabel = new Label("labelExample", String.valueOf(personService.getAllPerson().size()));
        markupLabel.setEscapeModelStrings(false);
        this.add(markupLabel);

        Button addPersonButton = new Button("addObjectButton") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                this.info("addPersonButton.onSubmit executed");
            }
        };

        Form form = new Form("form") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
                this.info("Form.onSubmit pre-executed");
                personService.addPerson(new Person());
                this.info("Form.onSubmit post-executed");
                this.setResponsePage(Home.class);
            }
        };

        form.add(addPersonButton);
        this.add(form);

    }
}
