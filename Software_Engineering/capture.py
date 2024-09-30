import cv2
import numpy as np

# 동영상 파일 경로
video_path = '10.mp4'

# 동영상 캡처 객체 생성
cap = cv2.VideoCapture(video_path)

# 동영상의 총 프레임 수와 FPS 가져오기
total_frames = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
fps = cap.get(cv2.CAP_PROP_FPS)
video_duration = total_frames / fps  # 총 재생 시간(초)

# 이전 프레임 초기화
prev_frame = None
slide_count = 14

# 프레임 간 차이를 계산하기 위한 임계값 설정
threshold = 3  # 필요에 따라 조정 가능

# 프레임 카운터 초기화
frame_counter = 0

# 영상 재생을 위한 창 생성 및 크기 조절 (선택 사항)
cv2.namedWindow('Video', cv2.WINDOW_NORMAL)
cv2.resizeWindow('Video', 200, 150)  # 원하는 크기로 조정

while cap.isOpened():
    ret, frame = cap.read()
    if not ret:
        break

    frame_counter += 1

    # 현재 재생 시간 계산
    current_time = frame_counter / fps  # 현재 재생 시간(초)

    # 재생 시간 텍스트 추가
    time_text = f'Time: {current_time:.2f}s / {video_duration:.2f}s'
    # cv2.putText(frame, time_text, (10, 30), cv2.FONT_HERSHEY_SIMPLEX,
    #             1, (255, 255, 255), 2, cv2.LINE_AA)

    # 진행률 계산
    progress = frame_counter / total_frames

    # 프로그레스 바 그리기
    bar_x = 10
    bar_y = frame.shape[0] - 30
    bar_width = frame.shape[1] - 20
    bar_height = 20

    # 배경 바 그리기
    # cv2.rectangle(frame, (bar_x, bar_y), (bar_x + bar_width, bar_y + bar_height),
    #               (50, 50, 50), -1)

    # 진행 바 그리기
    # cv2.rectangle(frame, (bar_x, bar_y),
    #              (bar_x + int(bar_width * progress), bar_y + bar_height),
    #             (0, 255, 0), -1)

    # 영상 표시를 위한 diff_mean 계산 (선택 사항)
    display_diff_mean = None

    # 매 5번째 프레임만 처리
    if frame_counter % 20 == 0:
        # 그레이스케일로 변환
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

        # 프레임 크기 조정 (처리 속도 향상을 위해 선택 사항)
        gray = cv2.resize(gray, (640, 480))

        # 노이즈 감소를 위한 블러링 적용 (선택 사항)
        gray = cv2.GaussianBlur(gray, (5, 5), 0)

        if prev_frame is None:
            # 첫 번째 프레임을 무조건 캡처
            slide_count += 1
            cv2.imwrite(f'slide_{slide_count}.png', frame)
            print(f'Slide {slide_count} (첫 번째 프레임) captured.')
            prev_frame = gray
        else:
            # 현재 프레임과 이전 프레임 간의 절대 차이 계산
            frame_diff = cv2.absdiff(prev_frame, gray)

            # 차이 이미지의 평균 계산
            diff_mean = np.mean(frame_diff)
            print(f'Frame {frame_counter}, diff_mean: {diff_mean}')

            # diff_mean 값을 영상에 표시하기 위해 저장
            display_diff_mean = diff_mean

            # 차이의 평균이 임계값보다 크면 슬라이드 변경으로 간주
            if diff_mean > threshold:
                slide_count += 1
                # 슬라이드 이미지 저장
                cv2.imwrite(f'slide_{slide_count}.png', frame)
                print(f'Slide {slide_count} captured.')

            # 이전 프레임은 항상 업데이트
            prev_frame = gray

    # diff_mean 값을 영상에 표시
    # if display_diff_mean is not None:
    #     diff_text = f'diff_mean: {display_diff_mean:.2f}'
    #     cv2.putText(frame, diff_text, (10, 70), cv2.FONT_HERSHEY_SIMPLEX,
    #                 1, (255, 255, 255), 2, cv2.LINE_AA)

    # 영상 표시
    cv2.imshow('Video', frame)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()