import java.util.ArrayList;
import java.util.Collection;

public class MaxHeap {
    private ArrayList<Student> students;

    public MaxHeap(int capacity) {
        students = new ArrayList<Student>(capacity);
    }

    public MaxHeap(Collection<Student> collection) {
        int temp=0;
        students = new ArrayList<Student>(collection);
        for (Student elt:students)
        {
            elt.setIndex(temp);
            temp++;
        }
        for (int i = size() / 2 - 1; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    public Student getMax() {
        if (size() < 1) {
            throw new IndexOutOfBoundsException("No maximum value:  the heap is empty.");
        }
        return students.get(0);
    }

    public Student extractMax() {
        Student value = getMax();
        students.set(0, students.get(size() - 1));
        students.remove(size() - 1);
        maxHeapify(0);
        return value;
    }

    public int size() {
        return students.size();
    }

    public void insert(Student elt) {
        students.add(elt);
       // int current = size() - 1; part 1
        elt.setIndex(size()-1);
        moveUp(elt.getIndex());
    }

    private void moveUp(int current) {
        while (students.get(current).compareTo(students.get(parent(current)))>0) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void addGrade(Student elt, double gradePointsPerUnit, int units) {
        elt.addGrade(gradePointsPerUnit,units);
        int current = elt.getIndex();
        moveUp(current);
        maxHeapify(current);
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int left(int index) {
        return 2 * index + 1;
    }

    private int right(int index) {
        return 2 * index + 2;
    }

    private void swap(int from, int to) {
        Student val = students.get(from);
        students.set(from, students.get(to));
        changeIndex(from,students.get(from));
        students.set(to, val);
        changeIndex(to,val);
    }

    private void maxHeapify(int index) {
        int left = left(index);
        int right = right(index);
        int largest = index;
        if (left < size() && students.get(left).compareTo(students.get(largest)) > 0) {
            largest = left;
        }
        if (right < size() && students.get(right).compareTo(students.get(largest)) > 0) {
            largest = right;
        }
        if (largest != index) {
            swap(index, largest);
            maxHeapify(largest);
        }
    }

    private void changeIndex(int index, Student elt)
    {
        elt.setIndex(index);
    }
}