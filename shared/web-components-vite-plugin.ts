import path from 'path';
import { PluginOption } from 'vite';

/**
 * Vite plugin that resolves Vaadin component JS modules to a
 * local checkout of the web-components repository
 *
 * @param webComponentsRepoPath
 */
export function useLocalWebComponents(webComponentsRepoPath: string): PluginOption {
  const nodeModulesPath = path.resolve(__dirname, '../', `${webComponentsRepoPath}/node_modules`);

  return {
    name: 'use-local-web-components',
    enforce: 'pre',
    config() {
      return {
        server: {
          fs: {
            allow: [nodeModulesPath]
          },
          watch: {
            ignored: [`!${nodeModulesPath}/**`]
          }
        }
      };
    },
    resolveId(id) {
      if (/^(@polymer|@vaadin)/.test(id)) {
        return this.resolve(path.join(nodeModulesPath, id));
      }
    }
  };
}

/**
 * Vite plugin that resolves Vaadin web components to Lit versions
 */
export function useLitWebComponents(): PluginOption {
  return {
    name: 'use-lit-web-components',
    config() {
      return {
        resolve: {
          alias: [
            'accordion',
            'accordion-panel',
            'app-layout',
            'drawer-toggle',
            'avatar',
            'avatar-group',
            'context-menu',
            'custom-field',
            'details',
            'dialog',
            'horizontal-layout',
            'list-box',
            'item',
            'notification',
            'radio-button',
            'radio-button-group',
            'scroller',
            'split-layout',
            'tabs',
            'tab',
            'tabsheet',
            'vertical-layout'
          ].flatMap((component) => {
            return [
              {
                find: new RegExp(`^@vaadin/[^\/]+\/(src\/)?vaadin-${component}.js`),
                replacement: `@vaadin/[^\/]+/$1vaadin-lit-${component}.js`
              }
            ];
          })
        }
      };
    }
  };
}
