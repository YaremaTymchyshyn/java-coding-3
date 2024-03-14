import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Publication
{
    private String title;
    private int year;
    private String publisher;

    public Publication(String title, int year, String publisher)
    {
        this.title = title;
        this.year = year;
        this.publisher = publisher;
    }

    public String getTitle()
    {
        return title;
    }

    public int getYear()
    {
        return year;
    }

    public String getPublisher()
    {
        return publisher;
    }

    abstract String getAdditionalInfo();
}

class Book extends Publication
{
    private String author;
    private int pageCount;

    public Book(String title, int year, String publisher, String author, int pageCount)
    {
        super(title, year, publisher);
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getAuthor()
    {
        return author;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    @Override
    String getAdditionalInfo()
    {
        return "Автор: " + author + ", Кількість сторінок: " + pageCount;
    }
}

class Magazine extends Publication
{
    private int number;
    private String genre;

    public Magazine(String title, int year, String publisher, int number, String genre)
    {
        super(title, year, publisher);
        this.number = number;
        this.genre = genre;
    }

    public int getNumber()
    {
        return number;
    }

    public String getGenre()
    {
        return genre;
    }

    @Override
    String getAdditionalInfo()
    {
        return "Номер: " + number + ", Жанр: " + genre;
    }
}

public class Main3
{
    public static void main(String[] args)
    {
        List<Publication> publications = new ArrayList<>();

        publications.add(new Book("Гаррі Поттер", 2000, "Прозоре Перо", "Автор 1", 921));
        publications.add(new Book("Вояк Швейк", 1995, "Фоліо", "Автор 2", 1543));
        publications.add(new Magazine("Бобік", 2021, "Філософська Стратегія", 3, "Розваги"));
        publications.add(new Magazine("Vogue", 2022, "АртЕкспрес", 47, "Мода"));
        publications.add(new Book("Відьмак", 2005, "Прозоре Перо", "Автор 2", 4078));
        publications.add(new Magazine("Котики", 2019, "Молодь", 39, "Тварини"));
        publications.add(new Book("Першому гравцю приготуватися", 2014, "Смолоскип", "Автор 3", 377));
        publications.add(new Book("Тореадори з Васюківки", 2002, "Видавництво Старого Лева", "Автор 2", 802));
        publications.add(new Magazine("Смайлик", 2017, "Молодь", 22, "Розваги"));


        publications.sort((a, b) -> a.getTitle().compareTo(b.getTitle()));

        Map<String, Integer> authorBookCounts = new HashMap<>();
        for (Publication publication : publications)
        {
            if (publication instanceof Book)
            {
                Book book = (Book) publication;
                authorBookCounts.put(book.getAuthor(), authorBookCounts.getOrDefault(book.getAuthor(), 0) + 1);
            }
        }

        String mostProductiveAuthor = "";
        int maxBookCount = 0;
        for (Map.Entry<String, Integer> entry : authorBookCounts.entrySet())
        {
            if (entry.getValue() > maxBookCount)
            {
                mostProductiveAuthor = entry.getKey();
                maxBookCount = entry.getValue();
            }
        }

        System.out.println("Список видань (відсортований за назвою):");
        for (Publication publication : publications)
        {
            System.out.println(publication.getTitle() + " (" + publication.getYear() + "), " + publication.getPublisher() + ": " + publication.getAdditionalInfo());
        }

        System.out.println("\nАвтор, який видав найбільше книжок: " + mostProductiveAuthor);

        System.out.println("\nЖурнали жанру 'Розваги':");
        for (Publication publication : publications)
        {
            if (publication instanceof Magazine)
            {
                Magazine magazine = (Magazine) publication;
                if ("Розваги".equals(magazine.getGenre()))
                {
                    System.out.println(magazine.getTitle() + " (" + magazine.getYear() + "), " + magazine.getPublisher() + ": " + magazine.getAdditionalInfo());
                }
            }
        }
    }
}
