package dao.spec;

@FunctionalInterface
public interface Specification<T> {

    boolean isSatisfiedBy(T t);

    default Specification<T> and(Specification<T> otherSpec){
        return t -> this.isSatisfiedBy(t) && otherSpec.isSatisfiedBy(t);
    }

    static <T> Specification<T> alwaysTrue(){
        return t -> true;
    }
}
