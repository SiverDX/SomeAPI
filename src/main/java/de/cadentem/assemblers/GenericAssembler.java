package de.cadentem.assemblers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.GenericTypeResolver;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.hateoas.server.core.EvoInflectorLinkRelationProvider;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

// https://github.com/spring-projects/spring-hateoas-examples/blob/main/commons/src/main/java/org/springframework/hateoas/SimpleIdentifiableRepresentationModelAssembler.java
public class GenericAssembler<T> implements SimpleRepresentationModelAssembler<T> {
    private final Class<?> controllerClass;

    @Getter
    private final LinkRelationProvider relProvider;

    @Getter
    private final Class<?> resourceType;

    @Getter
    @Setter
    private String basePath = "";

    public GenericAssembler(final Class<?> controllerClass, final LinkRelationProvider relProvider) {
        this.controllerClass = controllerClass;
        this.relProvider = relProvider;

        this.resourceType = GenericTypeResolver.resolveTypeArgument(this.getClass(), GenericAssembler.class);
    }

    public GenericAssembler(final Class<?> controllerClass) {
        this(controllerClass, new EvoInflectorLinkRelationProvider());
    }

    @Override
    public void addLinks(final EntityModel<T> resource) {
        // Example: /types/1
        resource.add(getCollectionBuilder().slash(getId(resource)).withSelfRel());
        // Example: /types
        resource.add(getCollectionBuilder().withRel(this.relProvider.getCollectionResourceRelFor(this.resourceType)));
    }

    @Override
    public void addLinks(final CollectionModel<EntityModel<T>> resources) {
        resources.add(getCollectionBuilder().withSelfRel());
    }

    protected LinkBuilder getCollectionBuilder() {
        WebMvcLinkBuilder builder = WebMvcLinkBuilder.linkTo(this.controllerClass);

        StringBuilder path = new StringBuilder();
        path.append(getPrefix());
        path.append(this.relProvider.getCollectionResourceRelFor(this.resourceType));

        /* Example: Accessing 'http://localhost:8080/types'
        builder = http://localhost:8080
        path = types
        */
        for (String pathComponent : path.toString().split("/")) {
            if (!pathComponent.isEmpty()) {
                builder = builder.slash(pathComponent);
            }
        }

        return builder;
    }

    // todo :: do this some other way?
    private Object getId(final EntityModel<T> resource) {
        Field id = ReflectionUtils.findField(this.resourceType, "id");
        ReflectionUtils.makeAccessible(id);

        return ReflectionUtils.getField(id, resource.getContent());
    }

    private String getPrefix() {
        return getBasePath().isEmpty() ? "" : getBasePath() + "/";
    }
}
