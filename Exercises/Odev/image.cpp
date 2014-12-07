#include <opencv/cv.h>
#include <opencv/highgui.h>
#include <stdlib.h>
#include <time.h>
using namespace cv;

const int slider_max = 8;
int slider;
Mat frame;
char* trackbar_type =
		"Type: \n 0: Normal \n 1: Negatif \n 2: Threshold \n 3: Otsu(S/B) \n 4: Gaussian(S/B) \n 5: RGB Change";

void on_trackbar(int, void*) {
	if (slider == 1) {
		for (int j = 0; j < frame.cols; ++j) {
			for (int i = 0; i < frame.rows; ++i) {
				frame.at<cv::Vec3b>(i, j)[0] = 255
						- frame.at<cv::Vec3b>(i, j)[0];
				frame.at<cv::Vec3b>(i, j)[1] = 255
						- frame.at<cv::Vec3b>(i, j)[1];
				frame.at<cv::Vec3b>(i, j)[2] = 255
						- frame.at<cv::Vec3b>(i, j)[2];
			}
		}
		/*
		 Mat all_ones = Mat(frame.rows,frame.cols,frame.type(),Scalar(1,1,1))*255;
		 subtract(all_ones,frame,frame);
		 */
	}

	if (slider == 2) {
		if (frame.channels() >= 3)
			cvtColor(frame, frame, CV_BGR2GRAY);
		for (int j = 0; j < frame.cols; ++j) {
			for (int i = 0; i < frame.rows; ++i) {
				if (frame.at<uchar>(i, j) > 100)
					frame.at<uchar>(i, j) = 255;
				else
					frame.at<uchar>(i, j) = 0;
			}
		}
	}

	if (slider == 3) {
		if (frame.channels() >= 3)
			cvtColor(frame, frame, CV_BGR2GRAY);
		threshold(frame, frame, 0, 255, THRESH_OTSU);
	}

	if (slider == 4) {
		if (frame.channels() >= 3)
			cvtColor(frame, frame, CV_BGR2GRAY);
		adaptiveThreshold(frame, frame, 250, CV_ADAPTIVE_THRESH_GAUSSIAN_C,
				CV_THRESH_BINARY, 5, 0);
	}

	if (slider == 5) {
		if (frame.channels() >= 3) {
			for (int j = 0; j < frame.cols; ++j) {
				for (int i = 0; i < frame.rows; ++i) {
					Vec3b temp = frame.at<cv::Vec3b>(i, j);
					frame.at<cv::Vec3b>(i, j)[0] = frame.at<cv::Vec3b>(i, j)[1];
					frame.at<cv::Vec3b>(i, j)[1] = frame.at<cv::Vec3b>(i, j)[2];
					frame.at<cv::Vec3b>(i, j)[2] = temp[0];
				}
			}
		}
	}

	if (slider == 6) {
		srand(time(NULL));
		int r = rand() % 100;
		if (frame.channels() >= 3) {
			for (int j = 0; j < frame.cols; ++j) {
				for (int i = 0; i < frame.rows; ++i) {
					frame.at<cv::Vec3b>(i, j)[0] = frame.at<cv::Vec3b>(i, j)[0]
							+ r * (i + j);
					frame.at<cv::Vec3b>(i, j)[1] = frame.at<cv::Vec3b>(i, j)[1]
							+ r * (i + j);
					frame.at<cv::Vec3b>(i, j)[2] = frame.at<cv::Vec3b>(i, j)[2]
							+ r * (i + j);
				}
			}
		}
	}

	if (slider == 7) {
		;
	}

	imshow("odev", frame);
}

int main(int argc, char** argv) {
	slider = 6;

	namedWindow("odev", 1);

	/// Create Trackbars
	char TrackbarName[50];
	sprintf(TrackbarName, "Alpha x %d", slider_max);

	createTrackbar(trackbar_type, "odev", &slider, slider_max, on_trackbar);

	/// Show some stuff

	CvCapture* capture;
	capture = cvCreateCameraCapture(0);
	cvSetCaptureProperty(capture, CV_CAP_PROP_FRAME_WIDTH, 320);
	cvSetCaptureProperty(capture, CV_CAP_PROP_FRAME_HEIGHT, 240);

	while (cvGrabFrame(capture)) {
		frame = cvRetrieveFrame(capture);

		on_trackbar(slider, 0);
		//-- 3. Apply the classifier to the frame
		if (waitKey(10) == 27)
			exit(0);
	}

	/// Wait until user press some key
	waitKey(0);

	return 0;
}
