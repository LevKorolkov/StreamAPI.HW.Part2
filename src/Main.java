import java.util.*;

public class Main {
    private static final int population = 10_000_000;
    private static final int ageLimit = 100;

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < population; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(ageLimit),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Integer countOfMinorPersons = (int) persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();

        List<String> familiesOfConscripts = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .filter(m -> m.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .toList();

        List<String> familiesOfWorkablePeoples = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 60 && x.getSex().equals(Sex.WOMAN))
                .filter(y -> y.getAge() >= 18 && y.getAge() <= 65 && y.getSex().equals(Sex.MAN))
                .filter(g -> g.getEducation().equals(Education.HIGHER))
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .toList();
    }

}