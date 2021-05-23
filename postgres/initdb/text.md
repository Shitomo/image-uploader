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
        
        
        
```java
fun numberingAuthor(bookId : Long, authors : List<Author>) {
        // 設問を採番
        var maxAuthorId  = bookMapper.getMaxAuthorId(bookId)
        for (author in authors) {
            if(author.id == -1L) {
                author.id = ++maxAuthorId;
            }
            // 選択肢を採番
            var maxFanId  = bookMapper.getMaxFanId(bookId, author.id)
            for (fan in author.fans) {
                if(fan.id == -1L) {
                    fan.id = ++maxFanId;
                }
            }
        }
    }
```

```json

{
  "id" : "1",
  "questions" : [
        {
                "qid" :"1"
                "answers" : [{"" : "1"}, {"answer" : "2"}]
        },
        {
                "qid" :"2"
                "answers" : [{"answer" : "1"}, {"answer" : "2"}]
        }
  ]
}

```
