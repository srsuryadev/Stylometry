
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Chart extends ApplicationFrame {

    public Chart(double avg1,double avg2,double pdp,double pdp1,double pds,double pds2)
    {
        super("stats");
        JFreeChart barChart = ChartFactory.createBarChart(
                "Authorstats",
                "Category",
                "Score",
                createDataset(avg1,avg2,pdp,pdp1,pds,pds2),
                PlotOrientation.VERTICAL,
                true, true, false);


        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
        setContentPane(chartPanel );
    }

    private CategoryDataset createDataset(double avg1,double avg2,double pdp,double pdp1,double pds,double pds2 )
    {

        final String a1 ="Author1";
        final String a2 ="Author2";
        final String nos="No of sentences";
        final String avgsl="Avg Sentence Length";
        final String pdpq=" Punctuation Density/ Para";
        final String pdsq=" Punctuation Density/Sentence";



        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset( );


       // dataset.addValue(nos1, a1 ,nos);
        dataset.addValue(avg1, a1 ,avgsl);
        dataset.addValue(pdp, a1 ,pdpq);
        dataset.addValue(pds, a1 ,pdsq);

       // dataset.addValue(nos2,a2 , nos);
        dataset.addValue(avg2, a2 ,avgsl);
        dataset.addValue(pdp1, a2 ,pdpq);
        dataset.addValue(pds2, a2 ,pdsq);


        return dataset;
    }
//
//            Chart m= new Main(nos1,avg1,nos2,avg2,pdp,pdp1,pds,pds2);
//            m.pack( );
//            RefineryUtilities.centerFrameOnScreen(m);
//            m.setVisible( true );
//
}
