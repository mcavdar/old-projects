/*#include "opencv2/highgui/highgui.hpp"
 #include "opencv2/imgproc/imgproc.hpp"
 #include <opencv/cv.h>
 #include "opencv2/opencv.hpp"
 #include <sys/timeb.h>

 using namespace cv;
 #include <opencv/cv.h>
 #include <opencv/highgui.h>


 #if defined(_MSC_VER) || defined(WIN32)  || defined(_WIN32) || defined(__WIN32__) \
    || defined(WIN64)    || defined(_WIN64) || defined(__WIN64__)
 int CLOCK()
 {
 return clock();
 }
 #endif

 #if defined(unix)        || defined(__unix)      || defined(__unix__) \
    || defined(linux)       || defined(__linux)     || defined(__linux__) \
    || defined(sun)         || defined(__sun) \
    || defined(BSD)         || defined(__OpenBSD__) || defined(__NetBSD__) \
    || defined(__FreeBSD__) || defined __DragonFly__ \
    || defined(sgi)         || defined(__sgi) \
    || defined(__MACOSX__)  || defined(__APPLE__) \
    || defined(__CYGWIN__)
 int CLOCK()
 {
 struct timespec t;
 clock_gettime(CLOCK_MONOTONIC,  &t);
 return (t.tv_sec * 1000)+(t.tv_nsec*1e-6);
 }
 #endif

 double _avgdur=0;
 int _fpsstart=0;
 double _avgfps=0;
 double _fps1sec=0;

 double avgdur(double newdur)
 {
 _avgdur=0.98*_avgdur+0.02*newdur;
 return _avgdur;
 }

 double avgfps()
 {
 if(CLOCK()-_fpsstart>1000)
 {
 _fpsstart=CLOCK();
 _avgfps=0.7*_avgfps+0.3*_fps1sec;
 _fps1sec=0;
 }

 _fps1sec++;
 return _avgfps;
 }

 void process(Mat& frame)
 {
 imshow("frame",frame);
 }

 int main(int argc, char** argv)
 {
 int frameno=0;
 Mat newC;
 CvCapture *capture = cvCaptureFromCAM(-1);
 cvSetCaptureProperty(capture, CV_CAP_PROP_FRAME_HEIGHT, 240);
 cvSetCaptureProperty(capture, CV_CAP_PROP_FRAME_WIDTH, 320);
 while (true)
 {


 clock_t start=CLOCK();


 IplImage *frame = cvRetrieveFrame(capture);
 //printf("%dx%d\n", frame->width, frame->height);
 newC = cvarrToMat(frame);
 process(newC);
 double dur = CLOCK()-start;
 printf("avg time per frame %f ms. fps %f. frameno = %d\n",avgdur(dur),avgfps(),frameno++ );
 if(waitKey(1)==27)
 exit(0);
 }
 return 0;
 }



 */

#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include <opencv/cv.h>
#include <sys/timeb.h>

#include "opencv2/objdetect/objdetect.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include <opencv/highgui.h>
#include <iostream>
#include <stdio.h>
#include <sys/timeb.h>

using namespace cv;

#if defined(_MSC_VER) || defined(WIN32)  || defined(_WIN32) || defined(__WIN32__) \
    || defined(WIN64)    || defined(_WIN64) || defined(__WIN64__)
int CLOCK()
{
	return clock();
}
#endif

#if defined(unix)        || defined(__unix)      || defined(__unix__) \
    || defined(linux)       || defined(__linux)     || defined(__linux__) \
    || defined(sun)         || defined(__sun) \
    || defined(BSD)         || defined(__OpenBSD__) || defined(__NetBSD__) \
    || defined(__FreeBSD__) || defined __DragonFly__ \
    || defined(sgi)         || defined(__sgi) \
    || defined(__MACOSX__)  || defined(__APPLE__) \
    || defined(__CYGWIN__)
int CLOCK() {
	struct timespec t;
	clock_gettime(CLOCK_MONOTONIC, &t);
	return (t.tv_sec * 1000) + (t.tv_nsec * 1e-6);
}
#endif

double _avgdur = 0;
int _fpsstart = 0;
double _avgfps = 0;
double _fps1sec = 0;

double avgdur(double newdur) {
	_avgdur = 0.98 * _avgdur + 0.02 * newdur;
	return _avgdur;
}

double avgfps() {
	if (CLOCK() - _fpsstart > 1000) {
		_fpsstart = CLOCK();
		_avgfps = 0.7 * _avgfps + 0.3 * _fps1sec;
		_fps1sec = 0;
	}

	_fps1sec++;
	return _avgfps;
}

void process(Mat& frame) {
	imshow("frame", frame);
}

int main(int argc, char** argv) {
	CvCapture* capture;
	int frameno = 0;
	cv::Mat frame;
	capture = cvCreateCameraCapture(0);
	//cap.set(CV_CAP_PROP_FRAME_WIDTH, 640);
	//cap.set(CV_CAP_PROP_FRAME_HEIGHT, 480);
	while(cvGrabFrame(capture)) {
		clock_t start = CLOCK();
		frame = cvRetrieveFrame(capture);


			process(frame);

		double dur = CLOCK() - start;
		printf("avg time per frame %f ms. fps %f. frameno = %d\n", avgdur(dur),
				avgfps(), frameno++);
		if (waitKey(1) == 27)
			exit(0);
	}
	return 0;
}
