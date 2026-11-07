//
//  YouTubeHeaderView.swift
//  SmartTube Native YouTube UI
//  Replicates the authentic YouTube top navigation bar with Cast, Notifications, Search, and Profile.
//

import SwiftUI
import SmartTubeIOSCore

public struct YouTubeHeaderView: View {
    @Environment(AuthService.self) private var auth
    @Binding var showSearch: Bool
    @Binding var showSettings: Bool

    public init(showSearch: Binding<Bool>, showSettings: Binding<Bool>) {
        self._showSearch = showSearch
        self._showSettings = showSettings
    }

    public var body: some View {
        HStack(spacing: 14) {
            // Left: YouTube Logo & Branding
            HStack(spacing: 6) {
                ZStack {
                    RoundedRectangle(cornerRadius: 6, style: .continuous)
                        .fill(Color.red)
                        .frame(width: 28, height: 20)
                    Image(systemName: "play.fill")
                        .font(.system(size: 10, weight: .bold))
                        .foregroundColor(.white)
                }

                Text("SmartTube")
                    .font(.system(size: 19, weight: .bold, design: .rounded))
                    .tracking(-0.5)
                    .foregroundStyle(.primary)
            }
            .accessibilityElement(children: .combine)
            .accessibilityLabel("SmartTube Home")

            Spacer()

            // Right Action Buttons
            HStack(spacing: 16) {
                // AirPlay / Cast button
                Button {
                    // AirPlay action
                } label: {
                    Image(systemName: "airplayvideo")
                        .font(.system(size: 18))
                        .foregroundStyle(.primary)
                }
                .buttonStyle(.plain)

                // Notification Bell
                Button {
                    // Notifications
                } label: {
                    Image(systemName: "bell")
                        .font(.system(size: 18))
                        .foregroundStyle(.primary)
                }
                .buttonStyle(.plain)

                // Search Icon
                Button {
                    showSearch = true
                } label: {
                    Image(systemName: "magnifyingglass")
                        .font(.system(size: 18, weight: .medium))
                        .foregroundStyle(.primary)
                }
                .buttonStyle(.plain)

                // Profile Avatar / Settings Button
                Button {
                    showSettings = true
                } label: {
                    if auth.isSignedIn, let avatarURL = auth.channelAvatarURL {
                        AsyncImage(url: avatarURL) { phase in
                            switch phase {
                            case .success(let image):
                                image.resizable()
                                    .aspectRatio(contentMode: .fill)
                                    .frame(width: 26, height: 26)
                                    .clipShape(Circle())
                            default:
                                fallbackAvatar
                            }
                        }
                    } else {
                        fallbackAvatar
                    }
                }
                .buttonStyle(.plain)
            }
        }
        .padding(.horizontal, 14)
        .padding(.vertical, 8)
        .background(Color(UIColor.systemBackground).opacity(0.95))
    }

    private var fallbackAvatar: some View {
        ZStack {
            Circle()
                .fill(Color.secondary.opacity(0.2))
                .frame(width: 26, height: 26)
            Image(systemName: "person.fill")
                .font(.system(size: 14))
                .foregroundStyle(.secondary)
        }
    }
}
