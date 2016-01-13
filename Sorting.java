public class Sorting {


  //the max size for the test arrays
  static int N_MAX = 1000;

  //=================================
  //START OF CODE TO IMPLEMENT
  //=================================
  
  
  
  public static void main(String[] args)
  {
    plotBubbleSortTest(N_MAX);
    plotCombSortTest(N_MAX);
    plotCocktailSortTest(N_MAX);
    plotAll();
  }
  
  public static int compare(String a, String b)
  {

    a = a.toLowerCase();
    b = b.toLowerCase(); //not case sensitive
    boolean aLonger = false;
    boolean bLonger = false;
    int length;

    if(a.length() > b.length())
    {
      length = b.length();
      aLonger = true;
    }
    else if (b.length() > a.length())
    {
      length = a.length();
      bLonger = true;
    }
    else
    {
      length = a.length(); //these statements check how long the for loop needs to go
    }

    for (int i=0; i<length; i++) 
    {
      if ( a.charAt(i) < b.charAt(i) )
      {
        return -1;
      }
      else if ( a.charAt(i) > b.charAt(i) )
      {
        return 1;
      }
    }

    if (aLonger == true) //if all letters are the same, the longer word is after the shortest word
    {
      return 1;
    }
    else if (bLonger == true)
    {
      return -1;
    }
    else
    {
      return 0;
    }

  }
  
  public static int bubbleSort(String[] input_array) 
  {

    boolean swapped = true;
    int timesCompared = 0;

    while(swapped)
    {
      swapped = false;

      for (int i=1; i<input_array.length-1; i++) 
      {
        if (compare(input_array[i-1], input_array[i]) == 1) //checks if a swap needs to be made
        {
          swap(input_array, i-1, i);
          swapped = true;
        }
        timesCompared += 1; //one comparison is completed even if no swap is made
      }
    }
    return timesCompared;
  }
  
  public static int combSort(String[] input_array) 
  {
    int gap = input_array.length;
    double shrink = 1.3;
    boolean swapped = true;
    int timesCompared = 0;

    while(swapped==true || gap != 1)
    {
      gap = (int)(gap/shrink);
      if (gap < 1) //avoids infinite loop with no more comparisons
      {
        gap = 1;
      }

      int i = 0;
      swapped = false;
      
      while (i+gap < input_array.length)
      {
        if (compare(input_array[i], input_array[i+gap]) > 0) //checks if a swap needs to be made
        {
          swap(input_array, i, i+gap);
          swapped = true;
        }
        timesCompared++; //one comparison is completed even if no swap is made
        i++;
      }
    }
    return timesCompared;
  }
  
  public static int cocktailSort(String[] input_array) 
  {
    int left  = 0;
    int right = input_array.length;
    boolean swapped = true;
    int comparisonsMade = 0;

    while((left<right) && (swapped == true))
    {
      swapped = false;
      for (int i = left; i<right-1; i++) 
      {
        if (compare(input_array[i], input_array[i+1]) == 1) //checks if swap needs to be made
        {
          swap(input_array, i, i+1);
          swapped = true;
        }
        comparisonsMade++;
      }
      right--; //highest value is already at the end of the array
      if (swapped=true)
      {
        swapped = false;
        for (int i = right; i>left; i--)
        {
          if (compare(input_array[i-1], input_array[i]) == 1)
          {
            swap(input_array, i, i-1);
            swapped = true;
          }
          comparisonsMade++;
        }
      }
      left++; //smallest value is at left side of array
    }
    return comparisonsMade;
  }
  
  //------ANSWER QUESTION 2D HERE--------
  
  /* the fastest sorting algorithm of these three is combsort by far, as it is able to move small values toward the end of an array
  to the beginning of the array far faster than the other two counterparts. As it can move high values and low values over a greater distance (gap)
  it requires less comparisons overall to finish the sorting of the array. */
  
  
  //-------------------------------------
  
  
  //=================================
  //END OF CODE TO IMPLEMENT
  //=================================
  
  public static void plotBubbleSortTest(int N_MAX) 
  {
    /* 
     * bubble_sort_results[N] = the number of comparisons made
     * when sorting an array of size N.
     */
    int[] bubble_sort_results = new int[N_MAX];
    
    /*
     * For each array size between 1 (the smallest array size)
     * and N_MAX (an upper limit passed to this method), we will:
     * 1) create a random test array
     * 2) sort it, and store the number of comparisons in bubble_sort_results
     * MAKE SURE THAT YOUR METHOD IS ACTUALLY SORTING THE TEST ARRAY!!!!!!
     */
    
    for (int i = 1; i < N_MAX; i++) 
    {
      String[] test_array = ArrayUtilities.getRandomNamesArray(i);
      bubble_sort_results[i] = bubbleSort(test_array);
    }
    
    // create a plot window
    // The argument passed to  PlotWindow is the title of the window
    PlotWindow pw = new PlotWindow("Bubble Sort!");
    
    // add a plot to the window using our results array
    /*
     *  The first argument for addPlot is a name for your data set
     *  The second argument for addPlot is an array of integers,
     *  In position "N" in the array, you should put the number of
     *  comparisons that your algorithm made, when sorting an array
     *  of size N. For example, bubble_sort_results[100] will contain
     *  the number of comparisons made for sorting an array of 100 elements
     */
    pw.addPlot("BubbleSort", bubble_sort_results);
  }
  
  public static void plotCombSortTest(int N_MAX) 
  {
    // the results array
    int[] comb_sort_results = new int[N_MAX];
    // test sorting for arrays from size 1 to N_MAX
    // MAKE SURE THAT YOUR METHOD IS ACTUALLY SORTING THE TEST ARRAY!!!!!!
    for (int i = 1; i < N_MAX; i++) {
      String[] test_array = ArrayUtilities.getRandomNamesArray(i);
      comb_sort_results[i] = combSort(test_array);
    }
    // create a plot window
    PlotWindow pw = new PlotWindow("Comb Sort!");
    // add a plot to the window using our results array
    pw.addPlot("CombSort", comb_sort_results);
  }   
  
  public static void plotCocktailSortTest(int N_MAX) 
  {
    int[] cocktail_sort_results = new int[N_MAX];

// test sorting for arrays from size 1 to N_MAX
    // MAKE SURE THAT YOUR METHOD IS ACTUALLY SORTING THE TEST ARRAY!!!!!!
    for (int i = 1; i < N_MAX; i++) 
    {
      String[] test_array = ArrayUtilities.getRandomNamesArray(i);
      cocktail_sort_results[i] = cocktailSort(test_array);
    }
    // create a plot window
    PlotWindow pw = new PlotWindow("Cocktail Sort!");
    // add a plot to the window using our results array
    pw.addPlot("CocktailSort", cocktail_sort_results);
  }   
  
  public static void plotAll() 
  {
    String[] test_array;
    int[] bubble_sort_results = new int[N_MAX];
    int[] comb_sort_results = new int[N_MAX];
    int[] cocktail_sort_results = new int[N_MAX];
    
    for (int i = 1; i < N_MAX; i++) {
      test_array = ArrayUtilities.getRandomNamesArray(i);
      bubble_sort_results[i] = bubbleSort(test_array);
      
      test_array = ArrayUtilities.getRandomNamesArray(i);
      comb_sort_results[i] = combSort(test_array);
      
      test_array = ArrayUtilities.getRandomNamesArray(i);
      cocktail_sort_results[i] = cocktailSort(test_array);
    }
    // create a plot window
    PlotWindow pw = new PlotWindow("All Sorts!");
    
    // add a plot to the window using all results array
    pw.addPlot("BubbleSort", bubble_sort_results);
    pw.addPlot("CombSort", comb_sort_results);
    pw.addPlot("CocktailSort", cocktail_sort_results);
  }

  public static void swap(String[] array, int b, int c)
  {
    String t = array[b];
    array[b] = array[c];
    array[c] = t;
  }
  
}

