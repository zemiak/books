# books

J2EE / JSF app for viewing a collection of eBooks
It is mentioned as an optimized web frontend for phones and tablets to allow to browse, search and download the books from the collection to a reading device.

## How to Build
This project is developed on NetBeans 7.3. It uses a Derby database called "books", so you have to create that one in the NetBeans first. You can run the app on a J2EE server of your choice.

## Project Status

This is highly unpolished, unconfigurable. The project assumes, that you are a non-english ebook collector. Therefore english books have separate folders.
Also author info/metadata storing is a little bit strange (URL files in the folder structure)

## License

GPL version 2 or later

## Folder structure

It is simple. First level are letters (the first letter of the Author's lastname).
The second level of folders are Author's names.

Books\
> A\
>> Altair, John\

>>> How to become a programmer DE.mobi 

>>> doc\
>>>> o.URL

>>>> w.URL

>>>> tags.txt

>>> en\

>>>> How to become a programmer EN.mobi

> B

> C

## URL Files

Content is simple. It looks like:
> URL=http://some.site.com/path

This kind of URL files are created by a browser when you drag and drop an URL from it to a folder.

### o.URL

Original website of an Author.

### w.URL

Wikipedia page of an Author.

### g.URL

Guttenberg page of an Author.

### works.URL

List of books of an Author.

## Tags

In the _doc/tags.txt_ file.

If there are multiple tags, separate it via semicolons. Tags are supported only on the author level, not per-book.
