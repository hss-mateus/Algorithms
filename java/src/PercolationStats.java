import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    // Number of iterations to perform
    private int trials;

    // The results of each random experiment
    private double[] results;

    // Side size of the grid
    private int n;

    // Instead of calculating the statistics in each method call, the values
    // are calculated once and stored here
    private double mean, stddev, confidenceHi, confidenceLo;

    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1)
            throw new IllegalArgumentException();

        this.n = n;
        this.trials = trials;
        results = new double[trials];

        runExperiments();
        calculateStats();
    }

    // Get grid side size, number of iterations and print the statistics results
    public static void main(String[] args) {
        var n = Integer.parseInt(args[0]);
        var t = Integer.parseInt(args[1]);
        var percStats = new PercolationStats(n, t);

        StdOut.printf("%-23s = %f%n", "mean", percStats.mean());
        StdOut.printf("%-23s = %f%n", "stddev", percStats.stddev());
        StdOut.printf("%-23s = [%f, %f]%n", "95% confidence interval",
                percStats.confidenceLo(),
                percStats.confidenceHi());
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }

    // Run the experiments T times (number of iterations)
    private void runExperiments() {
        for (int i = 0; i < trials; i++) {
            var perc = new Percolation(n);

            // Open random sites until the system percolates
            while (!perc.percolates()) {
                // Get random values for a row and a column
                var row = StdRandom.uniform(1, n+1);
                var col = StdRandom.uniform(1, n+1);

                // Open a site in that location
                perc.open(row, col);
            }

            // Stores the threshold of that system
            results[i] = perc.numberOfOpenSites() / (double) (n * n);
        }
    }

    // Stores the statistics in the class variables
    private void calculateStats() {
        mean = StdStats.mean(results);
        stddev = StdStats.stddev(results);
        confidenceLo = mean - ((1.96 * stddev) / Math.sqrt(trials));
        confidenceHi = mean + ((1.96 * stddev) / Math.sqrt(trials));
    }
}
