//
//  YouTubeTopicPillsView.swift
//  SmartTube Native YouTube UI
//  Horizontal filter chips replicating YouTube's top topic pills.
//

import SwiftUI

public struct YouTubeTopicPillsView: View {
    public let topics: [String] = [
        "Tất cả", "Âm nhạc", "Trò chơi", "Tin tức", "Trực tiếp",
        "Podcasts", "Phim ảnh", "Hoạt hình", "Mới tải lên", "Đã xem"
    ]
    
    @Binding var selectedTopic: String

    public init(selectedTopic: Binding<String>) {
        self._selectedTopic = selectedTopic
    }

    public var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack(spacing: 8) {
                // Explore button
                Button {
                    // Explore
                } label: {
                    HStack(spacing: 4) {
                        Image(systemName: "safari")
                            .font(.system(size: 14))
                        Text("Khám phá")
                            .font(.system(size: 13, weight: .medium))
                    }
                    .padding(.horizontal, 10)
                    .padding(.vertical, 6)
                    .background(Color.secondary.opacity(0.12))
                    .foregroundColor(.primary)
                    .cornerRadius(8)
                }
                .buttonStyle(.plain)

                Divider()
                    .frame(height: 20)
                    .padding(.horizontal, 2)

                ForEach(topics, id: \.self) { topic in
                    let isSelected = topic == selectedTopic
                    Button {
                        selectedTopic = topic
                    } label: {
                        Text(topic)
                            .font(.system(size: 13, weight: isSelected ? .semibold : .regular))
                            .padding(.horizontal, 12)
                            .padding(.vertical, 6)
                            .background(
                                isSelected ? Color.primary : Color.secondary.opacity(0.12)
                            )
                            .foregroundColor(
                                isSelected ? Color(UIColor.systemBackground) : Color.primary
                            )
                            .cornerRadius(8)
                    }
                    .buttonStyle(.plain)
                }
            }
            .padding(.horizontal, 12)
            .padding(.vertical, 6)
        }
    }
}
