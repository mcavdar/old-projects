#include <opencv/cv.h>
#include <opencv/highgui.h>
#include <stdlib.h>
#include <time.h>

using namespace cv;

const int slider_max = 12;
const int slider_max2 = 100;
int slider,slider2;
Mat frame;
int r;
char* trackbar_type =
		"Type: 0: Normal 1: Negatif \n 2: Threshold \n 3: Otsu(S/B) \n 4: Gaussian(S/B) \n 5: RGB Change";

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
		//srand(time(NULL));
		//int r = rand() % 100;

		if (frame.channels() >= 3) {
			for (int j = 0; j < frame.cols; ++j) {
				for (int i = 0; i < frame.rows; ++i) {
					frame.at<cv::Vec3b>(i, j)[0] = frame.at<cv::Vec3b>(i, j)[0]
							+ slider2;
					frame.at<cv::Vec3b>(i, j)[1] = frame.at<cv::Vec3b>(i, j)[1]
							+  slider2;
					frame.at<cv::Vec3b>(i, j)[2] = frame.at<cv::Vec3b>(i, j)[2]
							+  slider2;
				}
			}
		}
	}

	if (slider == 7) {
		if (frame.channels() >= 3)
			cvtColor(frame, frame, CV_BGR2GRAY);
		equalizeHist(frame, frame);
	}

	if (slider == 8) {
		if (frame.channels() >= 3)
			cvtColor(frame, frame, CV_BGR2GRAY);

	}

	if (slider == 9) {
		Mat all_r = Mat(frame.rows, frame.cols, frame.type(), Scalar(1, 1, 1))
				* slider2;
		frame += all_r;
	}

	if (slider == 10) {
		// max_value = 255 , min_value = 0 , new_max = 150 , new_min=10
		if (frame.channels() >= 3) {
			for (int j = 0; j < frame.cols; ++j) {
				for (int i = 0; i < frame.rows; ++i) {
					frame.at<cv::Vec3b>(i, j)[0] =
							((frame.at<cv::Vec3b>(i, j)[0] * 140) / 255) + 10;
					frame.at<cv::Vec3b>(i, j)[1] =
							((frame.at<cv::Vec3b>(i, j)[1] * 140) / 255) + 10;
					frame.at<cv::Vec3b>(i, j)[2] =
							((frame.at<cv::Vec3b>(i, j)[2] * 140) / 255) + 10;
				}
			}
		}
	}

	if (slider == 11) {
		// max_value = 150 , min_value = 10 , new_max = 255 , new_min=0
		if (frame.channels() >= 3) {
			for (int j = 0; j < frame.cols; ++j) {
				for (int i = 0; i < frame.rows; ++i) {
					frame.at<cv::Vec3b>(i, j)[0] =
							((frame.at<cv::Vec3b>(i, j)[0] * 140) / 255) + 10;
					frame.at<cv::Vec3b>(i, j)[1] =
							((frame.at<cv::Vec3b>(i, j)[1] * 140) / 255) + 10;
					frame.at<cv::Vec3b>(i, j)[2] =
							((frame.at<cv::Vec3b>(i, j)[2] * 140) / 255) + 10;
				}
			}
		}
		if (frame.channels() >= 3) {
			for (int j = 0; j < frame.cols; ++j) {
				for (int i = 0; i < frame.rows; ++i) {
					frame.at<cv::Vec3b>(i, j)[0] =
							(((frame.at<cv::Vec3b>(i, j)[0] - 10) * 255) / 140);
					frame.at<cv::Vec3b>(i, j)[1] =
							(((frame.at<cv::Vec3b>(i, j)[1] - 10) * 255) / 140);
					frame.at<cv::Vec3b>(i, j)[2] =
							(((frame.at<cv::Vec3b>(i, j)[2] - 10) * 255) / 140);
				}
			}
		}
	}



	imshow("odev", frame);
	/*
	 vector<Mat> bgr_planes;
	 split(frame, bgr_planes);

	 /// Establish the number of bins
	 int histSize = 256;

	 /// Set the ranges ( for B,G,R) )
	 float range[] = { 0, 256 };
	 const float* histRange = { range };

	 bool uniform = true;
	 bool accumulate = false;

	 Mat b_hist, g_hist, r_hist;

	 /// Compute the histograms:
	 calcHist(&bgr_planes[0], 1, 0, Mat(), b_hist, 1, &histSize, &histRange,
	 uniform, accumulate);
	 calcHist(&bgr_planes[1], 1, 0, Mat(), g_hist, 1, &histSize, &histRange,
	 uniform, accumulate);
	 calcHist(&bgr_planes[2], 1, 0, Mat(), r_hist, 1, &histSize, &histRange,
	 uniform, accumulate);

	 // Draw the histograms for B, G and R
	 int hist_w = 512;
	 int hist_h = 400;
	 int bin_w = cvRound((double) hist_w / histSize);

	 Mat histImage(hist_h, hist_w, CV_8UC3, Scalar(0, 0, 0));

	 /// Normalize the result to [ 0, histImage.rows ]
	 normalize(b_hist, b_hist, 0, histImage.rows, NORM_MINMAX, -1, Mat());
	 normalize(g_hist, g_hist, 0, histImage.rows, NORM_MINMAX, -1, Mat());
	 normalize(r_hist, r_hist, 0, histImage.rows, NORM_MINMAX, -1, Mat());

	 /// Draw for each channel
	 for (int i = 1; i < histSize; i++) {
	 line(histImage,
	 Point(bin_w * (i - 1),
	 hist_h - cvRound(b_hist.at<float>(i - 1))),
	 Point(bin_w * (i), hist_h - cvRound(b_hist.at<float>(i))),
	 Scalar(255, 0, 0), 2, 8, 0);
	 line(histImage,
	 Point(bin_w * (i - 1),
	 hist_h - cvRound(g_hist.at<float>(i - 1))),
	 Point(bin_w * (i), hist_h - cvRound(g_hist.at<float>(i))),
	 Scalar(0, 255, 0), 2, 8, 0);
	 line(histImage,
	 Point(bin_w * (i - 1),
	 hist_h - cvRound(r_hist.at<float>(i - 1))),
	 Point(bin_w * (i), hist_h - cvRound(r_hist.at<float>(i))),
	 Scalar(0, 0, 255), 2, 8, 0);
	 }

	 /// Display
	 namedWindow("calcHist Demo", CV_WINDOW_AUTOSIZE);
	 imshow("calcHist Demo", histImage);

	 */

}

int main(int argc, char** argv) {
	slider = 0;
	srand(time(NULL));
	r = rand() % 100;

	namedWindow("odev", 1);

	/// Create Trackbar
	char TrackbarName[50];
	sprintf(TrackbarName, "Alpha x %d", slider_max);
	createTrackbar(trackbar_type, "odev", &slider, slider_max, on_trackbar);
	createTrackbar("iki", "odev", &slider2, slider_max2, on_trackbar);

	CvCapture* capture;
	capture = cvCreateCameraCapture(0);
	cvSetCaptureProperty(capture, CV_CAP_PROP_FRAME_WIDTH, 320);
	cvSetCaptureProperty(capture, CV_CAP_PROP_FRAME_HEIGHT, 240);

	while (cvGrabFrame(capture)) {
		frame = cvRetrieveFrame(capture);

		on_trackbar(slider, 0);

		if (waitKey(10) == 27)
			exit(0);
	}

	waitKey(0);
	return 0;
}
