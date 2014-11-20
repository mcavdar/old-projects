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
#include <opencv/highgui.h>
#include <iostream>
#include <stdio.h>

using namespace cv;

/** Function Headers */
void detectAndDisplay(Mat frame);
/** Global variables */
String face_cascade_name =
		"/home/mc/opencv-2.4.9/data/haarcascades/haarcascade_frontalface_alt.xml";
String eyes_cascade_name =
		"/home/mc/opencv-2.4.9/data/haarcascades/haarcascade_eye_tree_eyeglasses.xml";
CascadeClassifier face_cascade;
CascadeClassifier eyes_cascade;
String window_name = "Capture - Face detection";

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


int main(int argc, char** argv) {

	if (!face_cascade.load(face_cascade_name)) {
		printf("--(!)Error loading\n");
		return -1;
	};
	if (!eyes_cascade.load(eyes_cascade_name)) {
		return -1;
		printf("--(!)Error loading\n");
	};

	CvCapture* capture;
	int frameno = 0;
	cv::Mat frame;
	capture = cvCreateCameraCapture(0);

	cvSetCaptureProperty(capture, CV_CAP_PROP_FRAME_WIDTH, 640);
	cvSetCaptureProperty(capture, CV_CAP_PROP_FRAME_HEIGHT, 480);

	while (cvGrabFrame(capture)) {
		clock_t start = CLOCK();
		frame = cvRetrieveFrame(capture);
		//-- 3. Apply the classifier to the frame
		detectAndDisplay(frame);
		double dur = CLOCK() - start;
		printf("avg time per frame %f ms. fps %f. frameno = %d\n", avgdur(dur),
				avgfps(), frameno++);
		if (waitKey(1) == 27)
			exit(0);
	}
	return 0;
}


/** @function detectAndDisplay */
void detectAndDisplay(Mat frame) {

	std::vector<Rect> faces;
	Mat frame_gray;

	cvtColor(frame, frame_gray, CV_BGR2GRAY);
	equalizeHist(frame_gray, frame_gray);

	//-- Detect faces
	face_cascade.detectMultiScale(frame_gray, faces, 1.1, 2,
			0 | CASCADE_SCALE_IMAGE, Size(30, 30));

	for (size_t i = 0; i < faces.size(); i++) {
		Point center(faces[i].x + faces[i].width * 0.5,
				faces[i].y + faces[i].height * 0.5);
		ellipse(frame, center,
				Size(faces[i].width * 0.5, faces[i].height * 0.5), 0, 0, 360,
				Scalar(255, 0, 255), 4, 8, 0);
		//Mat faceROI = frame_gray(faces[i]);
		//std::vector<Rect> eyes;

		//-- In each face, detect eyes
		/*eyes_cascade.detectMultiScale(faceROI, eyes, 1.1, 2,
		 0 | CASCADE_SCALE_IMAGE, Size(30, 30));

		 for (size_t j = 0; j < eyes.size(); j++) {
		 Point center(faces[i].x + eyes[j].x + eyes[j].width * 0.5,
		 faces[i].y + eyes[j].y + eyes[j].height * 0.5);
		 int radius = cvRound((eyes[j].width + eyes[j].height) * 0.25);
		 circle(frame, center, radius, Scalar(255, 0, 0), 4, 8, 0);
		 }*/
	}

	//-- Show what you got
	imshow(window_name, frame);
}

