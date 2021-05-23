```java
numberingAuthor(book.id, authors)
        bookMapper.insertAuthor(book.id, authors)
        bookMapper.insertFan(book.id, authors)

        val authors2 = mutableListOf<Author>(authorC,authorA, authorD)
        numberingAuthor(book.id, authors2)
        bookMapper.deleteAuthors(book.id)
        bookMapper.insertAuthor(book.id, authors2)
        bookMapper.insertFan(book.id, authors2)

        authorD.fans = fanGroup4
        val authors3 = mutableListOf<Author>(authorC,authorA, authorD)
        numberingAuthor(book.id, authors2)
        bookMapper.deleteAuthors(book.id)
        bookMapper.insertAuthor(book.id, authors2)
        bookMapper.insertFan(book.id, authors2)
        ```
