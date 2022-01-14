import 'package:flutter/material.dart';
import 'package:flutter_booklist/sql_helper.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Book List',
      theme: ThemeData(
          primarySwatch: Colors.brown,
          scaffoldBackgroundColor: const Color(0xFFFFFFFF)),
      home: const MyHomePage(title: 'Flutter Book List'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key key, @required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  TextEditingController titleController = TextEditingController();
  TextEditingController descController = TextEditingController();
  TextEditingController authorController = TextEditingController();
  TextEditingController yearController = TextEditingController();

  @override
  void initState() {
    refreshBooks();
    super.initState();
  }

  // Collect Data from DB
  List<Map<String, dynamic>> books = [];
  void refreshBooks() async {
    final data = await SQLHelper.getBooks();
    setState(() {
      books = data;
    });
  }

  @override
  Widget build(BuildContext context) {
    print(books);
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: ListView.builder(
          itemCount: books.length,
          itemBuilder: (context, index) => Card(
                color: Colors.brown,
                margin: const EdgeInsets.all(15),
                child: ListTile(
                  isThreeLine: true,
                  title: Text(books[index]['title'],
                      style: TextStyle(
                          fontWeight: FontWeight.bold,
                          // height: 5,
                          fontSize: 20,
                          color: Color(0xFFFFFFFF))),
                  subtitle: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text("by. " + books[index]['author'],
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 15,
                              color: Color(0xFFFFFFFF))),
                      Text(books[index]['year'],
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              height: 2,
                              color: Color(0xFFFFFFFF))),
                      Text(
                        books[index]['desc'],
                        style: TextStyle(color: Color(0xFFFFFFFF)),
                      ),
                    ],
                  ),
                  trailing: SizedBox(
                    width: 100,
                    child: Row(
                      children: [
                        IconButton(
                            onPressed: () => modalForm(books[index]['id']),
                            icon: const Icon(
                              Icons.edit,
                              color: Colors.white,
                            )),
                        IconButton(
                            onPressed: () => deleteBook(books[index]['id']),
                            icon: const Icon(
                              Icons.delete,
                              color: Colors.white,
                            ))
                      ],
                    ),
                  ),
                ),
              )),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          modalForm(null);
        },
        child: const Icon(Icons.add),
      ),
    );
  }

  //Function -> Add
  Future<void> addBook() async {
    await SQLHelper.addBook(titleController.text, authorController.text,
        yearController.text, descController.text);
    refreshBooks();
  }

  // Function -> Update
  Future<void> updateBooks(int id) async {
    await SQLHelper.updateBooks(id, titleController.text, authorController.text,
        yearController.text, descController.text);
    refreshBooks();
  }

  // Function -> Delete
  void deleteBook(int id) async {
    await SQLHelper.deleteBook(id);
    ScaffoldMessenger.of(context)
        .showSnackBar(const SnackBar(content: Text("Successfull Delete Book")));
    refreshBooks();
  }

  //Form Add
  void modalForm(int id) async {
    if (id != null) {
      final dataBooks = books.firstWhere((element) => element['id'] == id);
      titleController.text = dataBooks['title'];
      authorController.text = dataBooks['author'];
      yearController.text = dataBooks['year'];
      descController.text = dataBooks['desc'];
    }

    showModalBottomSheet(
        context: context,
        builder: (_) => Container(
              padding: const EdgeInsets.all(15),
              width: double.infinity,
              height: 800,
              child: SingleChildScrollView(
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  crossAxisAlignment: CrossAxisAlignment.end,
                  children: [
                    TextField(
                      controller: titleController,
                      decoration: const InputDecoration(hintText: 'Title'),
                    ),
                    const SizedBox(
                      height: 10,
                    ),
                    TextField(
                      controller: authorController,
                      decoration: const InputDecoration(hintText: 'Author'),
                    ),
                    TextField(
                      controller: yearController,
                      decoration: const InputDecoration(hintText: 'Year'),
                    ),
                    TextField(
                      controller: descController,
                      decoration: const InputDecoration(hintText: 'Desc'),
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    ElevatedButton(
                        onPressed: () async {
                          if (id == null) {
                            await addBook();
                          } else {
                            await updateBooks(id);
                          }

                          // await addBook();
                          titleController.text = '';
                          authorController.text = '';
                          yearController.text = '';
                          descController.text = '';
                          Navigator.pop(context);
                        },
                        child: Text(id == null ? 'Add' : 'Update'))
                  ],
                ),
              ),
            ));
  }
}
