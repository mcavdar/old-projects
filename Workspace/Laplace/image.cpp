#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/highgui/highgui.hpp"
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
timespec diff(timespec start, timespec end);
using namespace cv;

/** @function main */
int main( int argc, char** argv )
{
  Mat src, src_gray, dst;
  int kernel_size = 3;
  int scale = 1;
  int delta = 0;
  int ddepth = CV_16S;
  char* window_name = "Laplace Demo";

  int c;

  /// Load an image
  src = imread( "large.jpg");

  if( !src.data )
    { return -1; }

  /// Remove noise by blurring with a Gaussian filter
 // GaussianBlur( src, src, Size(3,3), 0, 0, BORDER_DEFAULT );

  /// Convert the image to grayscale
  cvtColor( src, src_gray, CV_RGB2GRAY );

  /// Create window
  namedWindow( window_name, CV_WINDOW_AUTOSIZE );

  /// Apply Laplace function
  Mat abs_dst;

  timespec time1, time2;
  	int temp;
  	clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &time1);



  Laplacian( src_gray, dst, ddepth, 7, scale, delta, BORDER_DEFAULT );


  clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &time2);
    	printf("%d,%d",diff(time1,time2).tv_sec,diff(time1,time2).tv_nsec);

  convertScaleAbs( dst, abs_dst );


  /// Show what you got
  imshow( window_name, abs_dst );
  Laplacian( src_gray, dst, ddepth, 1, scale, delta, BORDER_DEFAULT );
    convertScaleAbs( dst, abs_dst );

    imshow( "mc", abs_dst );

  waitKey(0);

  return 0;
  }





timespec diff(timespec start, timespec end)
{
	timespec temp;
	if ((end.tv_nsec-start.tv_nsec)<0) {
		temp.tv_sec = end.tv_sec-start.tv_sec-1;
		temp.tv_nsec = 1000000000+end.tv_nsec-start.tv_nsec;
	} else {
		temp.tv_sec = end.tv_sec-start.tv_sec;
		temp.tv_nsec = end.tv_nsec-start.tv_nsec;
	}
	return temp;
}
