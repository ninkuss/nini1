import java.lang.Math;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class Main {
    public static void main(String[] args) {
        int[] intArr = { 1, 150, 200, 100, 151 };
        System.out.println(firstTask(intArr));

        System.out.println(secondTask(intArr));

        Department department = new Department("Test");
        System.out.println(department);
        System.out.println(department.equals(new Department("test")));
        System.out.println(department.equals(new Department("Test")));

        fourthTask("Nini Kusradze");

        fifthTask();
    }

    // task #1
    public static double firstTask(int[] intArr) {
        int sum = 0;
        int count = 0;

        for (int i = 0; i < intArr.length; i++) {
            if (intArr[i] > 150) {
                sum = sum + intArr[i];
                count++;
            }
        }

        return (double) sum / count;
    }

    // task #2
    public static double secondTask(int[] intArr) {
        int product = 1;
        int count = 0;

        for (int i = 0; i < intArr.length; i++) {
            if (intArr[i] % 2 != 0) {
                product = product * intArr[i];
                count++;
            }
        }

        return Math.pow(product, 1.0 / count);
    }

    // task #4
    public static void fourthTask(String data) {
        try {
            File file = new File("task4.txt");
            new FileOutputStream(file).write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // task #5
    public static void fifthTask() {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("departments");
            document.appendChild(root);

            Element firstChild = document.createElement("department");
            root.appendChild(firstChild);

            Element name = document.createElement("name");
            name.appendChild(document.createTextNode("HR"));
            firstChild.appendChild(name);

            Element email = document.createElement("email");
            email.appendChild(document.createTextNode("hr@oracle.com"));
            firstChild.appendChild(email);

            Element secondChild = document.createElement("department");
            root.appendChild(secondChild);

            Element firstName = document.createElement("firstname");
            firstName.appendChild(document.createTextNode("IT"));
            secondChild.appendChild(firstName);

            Element emailSecond = document.createElement("email");
            emailSecond.appendChild(document.createTextNode("it@java.com"));
            secondChild.appendChild(emailSecond);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("task5.xml"));
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}

// task #3
class Department {
    String name;

    Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department: " + this.name;
    }

    @Override
    public boolean equals(Object ob) {
        if (!(ob instanceof Department)) {
            return false;
        }

        Department other = (Department) ob;

        return other.name.equals(this.name);
    }
}

