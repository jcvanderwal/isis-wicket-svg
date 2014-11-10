package org.isisaddons.wicket.svg.cpt.applib;

import org.apache.wicket.IGenericComponent;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

public abstract class AbstractSvgComponent<T> extends WebMarkupContainer implements IGenericComponent<T> {

    private static final long serialVersionUID = 1L;

    public AbstractSvgComponent(final String wicketId) {
        super(wicketId);
    }

    public AbstractSvgComponent(final String wicketId, final IModel<T> model) {
        super(wicketId, model);
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        // Must be attached to an special tag
        checkComponentTag(tag, getTag());

        // Default handling for component tag
        super.onComponentTag(tag);
    }

    protected abstract String getTag();

    @Override
    public IModel<T> getModel() {
        return (IModel<T>) getDefaultModel();
    }

    @Override
    public void setModel(final IModel<T> model) {
        setDefaultModel(model);
    }

    @Override
    public T getModelObject() {
        return getModel().getObject();
    }

    @Override
    public void setModelObject(final T object) {
        getModel().setObject(object);
    }

}
