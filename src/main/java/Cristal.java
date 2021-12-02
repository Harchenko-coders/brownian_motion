import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Cristal {
    private static int period = 5000;
    private static int time = 60000;
    private int _numberOfCells;
    private int _numberOfAtoms;
    private double _probability;
    private ArrayList<Integer> _cells;

    public Cristal(int numberOfCells, int numberOfAtoms, double probability)
    {
        _numberOfCells = numberOfCells;
        _numberOfAtoms = numberOfAtoms;
        _probability = probability;
        _cells = new ArrayList<Integer>(_numberOfCells);
        _cells.add(_numberOfAtoms);
        for (int i = 1; i < _numberOfCells; i++) {
            _cells.add(0);
        }
    }

    public void startSimulation(Boolean isWithLock)
    {
        var threads = new ArrayList<Thread>(_numberOfAtoms);
        for(int i = 0; i < _numberOfAtoms; i++)
        {
            var thread = new Thread(new AtomThread(isWithLock));
            threads.add(thread);
            thread.setName(String.valueOf(i));
            thread.start();
        }
        var timer = new Timer();
        timer.scheduleAtFixedRate(new PrintScreen(), period, period);
        try {
            System.out.println("Start");
            Thread.sleep(time);
            System.out.println("End");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
        for(int i = 0; i < _numberOfAtoms; i++) {
            threads.get(i).interrupt();
        }
    }

    class PrintScreen extends TimerTask {

        public void run() {
            showResultList();
        }

        private void showResultList() {
            synchronized (_cells) {
            int sum = 0;
            for(int i = 0; i < _numberOfCells; ++i) {
                sum += _cells.get(i);
            }
                System.out.println("Cells: " + _cells.toString() + " Sum : " + String.valueOf(sum));
            }
        }
    }

    class AtomThread implements Runnable {

        private Boolean _isWithLock;
        private int _currentIndex;

        AtomThread(Boolean isWithLock) {
            _isWithLock = isWithLock;
            _currentIndex = 0;
        }

        public void run() {
            while(true) {
                var rand = new Random();
                double m = rand.nextDouble();

                move(m);
            }
        }

        private void move(double random)
        {
            if (random > _probability)
                update("right");
            else
                update("left");
        }

        private void update(String direction)
        {
            int add = 0;
            if (direction.equals("right")) {
                if (_currentIndex < _cells.size() - 1) {
                    add = -1;
                    _currentIndex++;
                }
            } else {
                if (_currentIndex > 0) {
                    add = 1;
                    _currentIndex--;
                }
            }

            if(_isWithLock)
                _updateWithLock(add);
            else
                _updateWithoutLock(add);
        }

        private void _updateWithLock(int add)
        {
            synchronized (_cells) {
                _cells.set(_currentIndex + add, _cells.get(_currentIndex + add) - 1);
                _cells.set(_currentIndex, _cells.get(_currentIndex) + 1);
            }
        }

        private void _updateWithoutLock(int add)
        {
            _cells.set(_currentIndex + add, _cells.get(_currentIndex + add) - 1);
            _cells.set(_currentIndex, _cells.get(_currentIndex) + 1);
        }
    }
}


